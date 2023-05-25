package org.apcs;

import com.google.common.base.Throwables;
import org.apcs.ast.Value;
import org.apcs.core.Environment;
import org.apcs.lexer.CharacterStream;
import org.apcs.lexer.Lexer;
import org.apcs.lexer.Position;
import org.apcs.lexer.TokenStream;
import org.apcs.parser.Parser;
import org.apcs.parser.ParserException;

import java.util.function.Supplier;


/**
 * An interpreter which evaluates whatever input is given and prints any errors.
 */
public class Interpreter {
    private final Environment env;
    private Position position;

    /**
     * Constructs a new interpreter and does all initializing
     */
    public Interpreter() {
        try {
            env = new Environment();
        } catch (Exception e) {
            throw new RuntimeException("Fatal error in Interpreter initialization", e);
        }

        // Load the standard library from the resources directory
        eval(new CharacterStream(getClass().getResourceAsStream("/stdlib.lisp")));
    }

    /**
     * Evalues the current input with the interpreter environment
     * @param source the string which it should takes all values from
     */
    public void eval(String source) {
        eval(new CharacterStream(source));
    }

    /**
     * Evalues the current input with the interpreter environment
     * @param stream the characters which it should take all values from
     */
    public void eval(CharacterStream stream) {
        eval(new Lexer(stream));
    }

    /**
     * Evalues the current input with the interpreter environment
     * @param source the lexer which it should takes all values from
     */
    public void eval(TokenStream source) {
        var parser = new Parser(source);
        eval(parser);
    }

    /**
     * Evalues the current input with the interpreter environment
     * @param source the parser which it should takes all values from
     */
    public void eval(Parser source) {
        position = source.getPosition();

        while (source.hasNext()) {
            eval(source::next);
        }
    }

    /**
     * Evaluates a value within the interpreter's environment and prints errors, if any
     * @param value the value to evaluate
     */
    public void eval(Supplier<Value<?>> value) {
        try {
            value.get().eval(env);
        } catch (ParserException e) {
            System.err.println("Syntax Error: " + position + ":");
            System.err.println(Throwables.getRootCause(e).getMessage().indent(4));
            System.exit(-1);
        } catch (LispException e) {
            System.err.println("Error: " + position + ":");
            System.err.println(Throwables.getRootCause(e).getMessage().indent(4));
            System.exit(-1);
        } catch (Exception e) {
            System.err.println("Fatal internal error: ");
            e.printStackTrace();
        }
    }

    public void eval(Value<?> val) {
        eval(() -> val);
    }

    public Environment getEnvironment() {
        return env;
    }

    public Position getPosition() {
        return position;
    }
}
