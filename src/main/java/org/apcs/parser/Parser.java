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

import org.apcs.ast.*;
import org.apcs.core.InternalException;
import org.apcs.lexer.Position;
import org.apcs.lexer.Token;
import org.apcs.lexer.TokenStream;
import org.apcs.lexer.TokenType;
import org.apcs.util.PeekableIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * A parser, which is an iterator of Values. This consumes input every time the next or parse methods are called and
 * keeps track of positions via getPosition().
 */
public class Parser implements Iterator<Value<?>> {
    private final PeekableIterator<Token> lexer;
    private final Position position;

    public Parser(TokenStream lexer) {
        this.position = lexer.getPosition();
        this.lexer = PeekableIterator.of(lexer);
    }

    @Override
    public boolean hasNext() {
        return lexer.hasNext();
    }

    @Override
    public Value<?> next() throws NoSuchElementException {
        try {
            return parse();
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }
    }

    public Value<?> parse() throws ParserException {
        if (!lexer.hasNext()) {
            throw new ParserException("No more tokens");
        } else if (lexer.peek().isSymbol()) {
            return new SymbolValue((String) lexer.next().value());
        } else if (lexer.peek().isNumber()) {
            return new NumberValue((Double) lexer.next().value());
        } else if (lexer.peek().type() == TokenType.BOOL) {
            return new BoolValue((boolean) lexer.next().value());
        } else if (lexer.peek().isString()) {
            return new StringValue((String) lexer.next().value());
        } else if (lexer.peek().isLeftParen()) {
            List<Value<?>> values = new ArrayList<>();
            lexer.next();

            while (!lexer.peek().isRightParen()) {
                values.add(parse());

                if (!lexer.hasNext()) throw new ParserException("Unexpected end of file");
            }

            lexer.next();
            return new ListValue(values);
        } else if (lexer.peek().type() == TokenType.QUOTE) {
            lexer.next();
            return new ListValue(new SymbolValue("quote"), parse());
        } else if (lexer.peek().type() == TokenType.QUASI_QUOTE) {
            lexer.next();
            return new ListValue(new SymbolValue("quasiquote"), parse());
        } else if (lexer.peek().type() == TokenType.UNQUOTE) {
            lexer.next();
            return new ListValue(new SymbolValue("unquote"), parse());
        } else if (lexer.peek().isRightParen()) {
            throw new ParserException("Unexpected right parenthesis");
        }

        throw new ParserException(new InternalException("Unable to match token type"));
    }

    public Position getPosition() {
        return position;
    }
}
