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

package org.apcs.ast;

/**
 * The Lisp representation of a string, represented by a String.
 *
 * @param value the String that represents the lisp string
 */
public record StringValue(String value) implements Value {
    public String toString() {
        return value;
    }

    /**
     * Returns the name of the lisp type
     *
     * @return the name of the lisp value
     */
    @Override
    public String typeName() {
        return "string";
    }
}