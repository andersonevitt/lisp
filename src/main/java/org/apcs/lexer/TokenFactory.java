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


import lombok.NonNull;

public class TokenFactory {
    private static final Token LEFT_PAREN = new LeftParen();
    private static final Token RIGHT_PAREN = new RightParen();


    public static Token getRightParen() {
        return RIGHT_PAREN;
    }

    public static Token getLeftParen() {
        return LEFT_PAREN;
    }

    public static Token getString(String value) {
        // TODO: See if interning would actually yields better performance
        return new StringToken(value);
    }

    public static Token getAtomOrNumber(@NonNull String matched) {
        try {
            return getNumber(Integer.parseInt(matched));
        } catch (NumberFormatException e) {
            return getAtom(matched);
        }
    }

    public static Token getNumber(int value) {
        return new Number(value);
    }

    public static Token getAtom(@NonNull String name) {
        return new Symbol(name);
    }
}
