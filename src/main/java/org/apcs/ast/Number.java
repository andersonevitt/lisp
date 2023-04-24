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
import lombok.Data;
import org.apcs.inter.Environment;

@Data
@AllArgsConstructor
public class Number implements Value {
    int value;

    public String toString() {
        return Integer.toString(value);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public Value eval(Environment env) {
        return this;
    }
}
