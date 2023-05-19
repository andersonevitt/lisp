package org.apcs.lexer;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

import java.util.Objects;

public class Token {
    private static final Interner<Token> interner = Interners.newWeakInterner();
    private final TokenType type;
    private final Object value;

    /**
     * Private constructor for initializing objects
     *
     * @param type  token type
     * @param value token value
     */
    private Token(TokenType type, Object value) {
        this.type = Objects.requireNonNull(type);
        this.value = value;
    }

    /**
     * Constructs a new Token with the given type.
     * Is equivalent to Token.of(type, null)
     *
     * @param type the token type
     */
    public static Token of(TokenType type) {
        return of(type, null);
    }

    /**
     * Constructs a new Token with the given type and value
     *
     * @param type  the token type
     * @param value the value associated with the token
     */
    public static Token of(TokenType type, Object value) {
        return interner.intern(new Token(type, value));
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

    public String toString() {
        var str = type().name();

        if (value() == null)
            return str;
        else
            return str + "(" + value() + ")";
    }
}
