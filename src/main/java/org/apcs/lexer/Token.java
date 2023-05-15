package org.apcs.lexer;

public record Token(TokenType type, Object value) {
    public Token(TokenType type) {
        this(type, null);
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
}
