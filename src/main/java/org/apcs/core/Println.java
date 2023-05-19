package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.Builtins.requireArity;

@Define("println")
public class Println implements BuiltinValue {
    /**
     * Prints the given arguments on a new line
     * @param env  the current environment to use
     * @param args the arguments to the function
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        requireArity(args, 1);
        System.out.println(args.get(0).eval(env));
        return ListValue.nil();
    }
}
