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

public abstract sealed class Token permits Symbol, LeftParen, NumberToken, RightParen, StringToken {

    public abstract Object getValue();

    public boolean isLeftParen() {
        return this instanceof LeftParen;
    }

    public boolean isRightParen() {
        return this instanceof RightParen;
    }

    public boolean isSymbol() {
        return this instanceof Symbol;
    }

    public boolean isNumber() {
        return this instanceof NumberToken;
    }

    public boolean isString() {
        return this instanceof StringToken;
    }


}
