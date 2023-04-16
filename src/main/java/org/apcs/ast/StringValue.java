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

import lombok.AllArgsConstructor;
import org.apcs.lexer.StringToken;
import org.apcs.parser.Environment;

@AllArgsConstructor
public class StringValue implements Value {
    private final String value;

    public boolean equals(StringToken other) {
        return other.getValue().equals(value);
    }

    public String toString() {
        return "\"" + value + "\"";
    }

    public String getValue() {
        return value;


    }

    public Value eval(Environment env) {
        return this;
    }
}
