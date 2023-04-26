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

import org.apcs.util.PeekableIterable;
import org.apcs.util.PeekableIterator;

import java.util.Iterator;

import static org.apcs.lexer.TokenFactory.*;
import static org.apcs.util.CharacterUtils.isWhitespace;

public class Lexer implements Iterator<Token>, PeekableIterable<Token> {
    private final PeekableIterator<Character> iterator;
    private final Position position;

    public Lexer(final CharacterStream stream) {
        this.position = stream.getPosition();
        this.iterator = PeekableIterator.of(stream);
    }

    @Override
    public boolean hasNext() {
        skipWhitespace();

        return iterator.hasNext();
    }

    @Override
    public Token next() {
        skipWhitespace();

        switch (iterator.peek()) {
            case '(' -> {
                iterator.next();
                return getLeftParen();
            }

            case ')' -> {
                iterator.next();
                return getRightParen();
            }

            case '\'' -> {
                iterator.next();
                return new Token(TokenType.QUOTE);
            }

            case '\"' -> {
                iterator.next();
                StringBuilder matched = new StringBuilder();

                while (iterator.peek() != '\"') {
                    matched.append(iterator.next());
                }

                iterator.next();

                return getString(matched.toString());
            }

            case ';' -> {
                while (iterator.peek() != '\n') iterator.next();

                // TODO: This could potentially cause a stack overflow in really large numbers and is *slightly*
                //  inefficient
                return next();
            }

            case '`' -> {
                iterator.next();
                return new Token(TokenType.QUASI_QUOTE);
            }

            case ',' -> {
                iterator.next();
                return new Token(TokenType.UNQUOTE);
            }

            // Symbol
            default -> {
                StringBuilder matched = new StringBuilder(8);

                while (iterator.hasNext() && !isWhitespace(iterator.peek()) && iterator.peek() != ')') {
                    matched.append(iterator.next());
                }

                return getAtomOrNumber(matched.toString());
            }
        }
    }

    private void skipWhitespace() {
        while (iterator.hasNext() && isWhitespace(iterator.peek())) {
            iterator.next();
        }
    }

    // TODO: This might be a bad idea
    @Override
    public Iterator<Token> iterator() {
        return this;
    }

    public Position getPosition() {
        return position;
    }
}
