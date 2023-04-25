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
import lombok.EqualsAndHashCode;
import org.apcs.std.Environment;

@AllArgsConstructor
@EqualsAndHashCode
public class Symbol implements Value {
    String name;

    public String toString() {
        return name;
    }

    @Override
    public Object getValue() {
        return name;
    }

    @Override
    public Value eval(Environment env) {
        return env.get(name);
    }
}