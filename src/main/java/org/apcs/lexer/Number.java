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


public final class Number extends Token {
    private final int value;

    Number(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public String toString() {
        return "NUMBER(" + value + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Number)) return false;
        final Number other = (Number) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getValue() != other.getValue()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {return other instanceof Number;}

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getValue();
        return result;
    }
}
