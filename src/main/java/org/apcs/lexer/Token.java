package org.apcs.lexer;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

import java.util.Objects;

/**
 * A token which is to be used by the parser and represents the smalled unit of a lisp expression
 *
 * @author Anderson Evitt, Zoe Schauder, Carly Linquist
 */
public class Token {
    private static final Interner<Token> interner = Interners.newWeakInterner();
    private final TokenType type;
    private final Object value;
    private final Position position;

    /**
     * Private constructor for initializing objects
     *
     * @param type  token type
     * @param value token value
     */
    private Token(TokenType type, Object value, Position where) {
        this.type = Objects.requireNonNull(type);
        this.value = value;
        position = new Position(where);
    }

    /**
     * Constructs a new Token with the given type.
     * Is equivalent to Token.of(type, null)
     *
     * @param type the token type
     */
    public static Token of(TokenType type, Position where) {
        return of(type, null, where);
    }

    /**
     * Constructs a new Token with the given type and value
     *
     * @param type  the token type
     * @param value the value associated with the token
     */
    public static Token of(TokenType type, Object value, Position where) {
        return interner.intern(new Token(type, value, where));
    }

    public boolean isSymbol() {
        return type == TokenType.SYMBOL;
    }

    public boolean isNumber() {
        return type == TokenType.NUMBER;
    }

    public boolean isString() {
        return type == TokenType.STRING;
    }

    public boolean isLeftParen() {
        return type == TokenType.LEFT_PAREN;
    }

    public boolean isRightParen() {
        return type == TokenType.RIGHT_PAREN;
    }

    public TokenType type() {
        return type;
    }

    public Object value() {
        return value;
    }

    public Position getPosition() {
        return position;
    }

    public String toString() {
        var str = type.name();

        if (value == null)
            return str;
        else
            return str + "(" + value + ")";
    }
}
