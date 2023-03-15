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

package org.apcs.parser;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apcs.lexer.Token;
import org.apcs.util.PeekableIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Slf4j
@ToString
public class Parser implements Iterator<Value> {
    @Getter
    @NonNull
    private final PeekableIterator<Token> lexer;

    public Parser(Iterator<Token> lexer) {
        this.lexer = PeekableIterator.of(lexer);
    }

    @Override
    public boolean hasNext() {
        return lexer.hasNext();
    }

    @Override
    public Value next() {
        return parse();
    }

    public Value parse() {
        if (!lexer.hasNext()) {
            log.error("No more tokens");
            throw new ParserException("No more tokens");
        }

        if (lexer.peek().isSymbol()) {
            log.trace("Parsed atom");
            return new Atom((String) lexer.next().getValue());
        }

        if (lexer.peek().isNumber()) {
            log.trace("Parsed number");
            return new Number((int) lexer.next().getValue());
        }

        if (lexer.peek().isString()) {
            return new StringValue((String) lexer.next().getValue());
        }

        if (lexer.peek().isLeftParen()) {
            List<Value> values = new ArrayList<>();
            lexer.next();

            while (!lexer.peek().isRightParen()) {
                values.add(parse());
            }

            lexer.next();

            return new ListValue(values);
        }

        if (lexer.peek().isRightParen()) {
            throw new ParserException("Unexcepted right parenthesis at");
        }

        log.error("Unable to match token. Peek = {}", lexer.peek());
        throw new IllegalStateException("Unable to match token");
    }
}
