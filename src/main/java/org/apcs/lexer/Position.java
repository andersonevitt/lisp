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


public class Position {
    private int line;
    private int column;

    public Position() {
        line = 0;
        column = 0;
    }

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public void nextLine() {
        line += 1;
    }

    public void nextColumn() {
        column += 1;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Position)) return false;
        final Position other = (Position) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.line != other.line) return false;
        if (this.column != other.column) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {return other instanceof Position;}

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.line;
        result = result * PRIME + this.column;
        return result;
    }

    public String toString() {return "Position(line=" + this.line + ", column=" + this.column + ")";}

    public int getLine() {return this.line;}

    public int getColumn() {return this.column;}

    public Position setLine(int line) {
        this.line = line;
        return this;
    }

    public Position setColumn(int column) {
        this.column = column;
        return this;
    }
}
