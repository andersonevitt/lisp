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

package org.apcs;

import org.apcs.lexer.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.apcs.lexer.TokenFactory.*;

public class LexerTests {

    private Lexer createLexer(String values) {
        return new Lexer(new CharacterStream(values));
    }

    // Asserts input matches values
    private void assertMatches(String input, List<Token> values) {
        var lexer = createLexer(input);

        List<Token> inputList = new ArrayList<>();
        lexer.forEachRemaining(inputList::add);

        assertEquals(inputList, values);
    }

    private void assertMatches(String input, Token... values) {
        assertMatches(input, List.of(values));
    }

    @Test
    void basicTokens() {
        assertMatches("(", getLeftParen());
        assertMatches(")", getRightParen());
        assertMatches("some", getAtom("some"));
        assertMatches("10", getNumber(10));
    }

    @Test
    void nestedParenthesis() {
        assertMatches("()", getLeftParen(), getRightParen());
        assertMatches("(())", getLeftParen(), getLeftParen(), getRightParen(), getRightParen());
        assertMatches("(some)", getLeftParen(), getAtom("some"), getRightParen());
        assertMatches("(some 10)", getLeftParen(), getAtom("some"), getNumber(10), getRightParen());
    }
}
