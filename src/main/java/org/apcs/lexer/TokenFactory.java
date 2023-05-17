/*
 * Copyright (C) 2023 Anderson Evitt, Zoe Schauder, Carly Linquist
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.apcs.lexer;

public class TokenFactory {

    private static final Token LEFT_PAREN = new Token(TokenType.LEFT_PAREN);
    private static final Token RIGHT_PAREN = new Token(TokenType.RIGHT_PAREN);

    private TokenFactory() {
    }

    public static Token getRightParen() {
        return RIGHT_PAREN;
    }

    public static Token getLeftParen() {
        return LEFT_PAREN;
    }

    public static Token getString(String value) {
        // TODO: See if interning would actually yields better performance
        return new Token(TokenType.STRING, value.intern());
    }

    public static Token getSymbolBoolOrNumber(String matched) {
        if ("true".equals(matched)) {
            return new Token(TokenType.BOOL, true);
        } else if ("false".equals(matched)) {
            return new Token(TokenType.BOOL, false);
        }

        try {
            return getNumber(Integer.parseInt(matched));
        } catch (NumberFormatException e) {
            return getSymbol(matched);
        }
    }

    public static Token getNumber(double value) {
        return new Token(TokenType.NUMBER, value);
    }

    public static Token getSymbol(String name) {
        return new Token(TokenType.SYMBOL, name);
    }
}
