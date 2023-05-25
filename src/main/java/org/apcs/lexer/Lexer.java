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

import org.apcs.util.PeekableIterator;

import static org.apcs.lexer.TokenType.*;


/**
 * A lexer which is an iterator of tokens that will be used in the parser.
 *
 * @author Anderson Evitt, Zoe Schauder
 */
public class Lexer implements TokenStream {
    private final PeekableIterator<Character> iterator;
    private final Position position;

    /**
     * Constructs Lexer with position from a stream of characters
     *
     * @param stream the stream of characters to lex
     */
    public Lexer(final CharacterStream stream) {
        this.position = stream.getPosition();
        this.iterator = PeekableIterator.of(stream);
    }

    /**
     * Returns true or false in the instance
     * that the iterator contains a value
     *
     * @return whether the next space is full or empty
     */
    @Override
    public boolean hasNext() {
        skipWhitespace();

        return iterator.hasNext();
    }

    /**
     * Checks the value of the position that the lexer is at.
     * Checks if the value is a left parentheses, right parentheses, or other token
     * Default case matches symbol or number while there is no right parentheses
     *
     * @return the current token
     */
    @Override
    public Token next() {
        skipWhitespace();

        switch (iterator.peek()) {
            case '(' -> {
                iterator.next();
                return Token.of(LEFT_PAREN, getPosition());
            }

            case ')' -> {
                iterator.next();
                return Token.of(RIGHT_PAREN, getPosition());
            }

            // Quote
            case '\'' -> {
                iterator.next();
                return Token.of(QUOTE, getPosition());
            }

            // Strings
            case '\"' -> {
                iterator.next();
                StringBuilder matched = new StringBuilder();

                while (iterator.peek() != '\"') {
                    matched.append(iterator.next());
                }

                iterator.next();

                return Token.of(STRING, matched.toString(), getPosition());
            }

            // Comments
            case ';' -> {
                while (iterator.peek() != '\n') iterator.next();

                return next();
            }

            // Quasi-quote
            case '`' -> {
                iterator.next();
                return Token.of(TokenType.QUASI_QUOTE, getPosition());
            }

            // unquote
            case ',' -> {
                iterator.next();
                return Token.of(TokenType.UNQUOTE, getPosition());
            }

            // Symbol
            default -> {
                StringBuilder matched = new StringBuilder(8);

                while (iterator.hasNext() && !Character.isWhitespace(iterator.peek()) && iterator.peek() != ')') {
                    matched.append(iterator.next());
                }

                var str = matched.toString();

                if ("true".equals(str)) {
                    return Token.of(TokenType.BOOL, true, getPosition());
                } else if ("false".equals(str)) {
                    return Token.of(TokenType.BOOL, false, getPosition());
                }

                try {
                    return Token.of(NUMBER, Double.parseDouble(str), getPosition());
                } catch (NumberFormatException e) {
                    return Token.of(SYMBOL, str, getPosition());
                }
            }
        }
    }

    /**
     * Lexer continues to move on to the next space
     */
    private void skipWhitespace() {
        while (iterator.hasNext() && Character.isWhitespace(iterator.peek())) {
            iterator.next();
        }
    }


    /**
     * Returns the position of lexer
     *
     * @return the line and column
     */
    @Override
    public Position getPosition() {
        return position;
    }
}
