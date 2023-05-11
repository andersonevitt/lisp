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
        line = 1;
        column = 1;
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

    public boolean equals(final Position o) {
        return this.line == o.line && this.column == o.column;
    }

    public String toString() {
        return "Position(line=" + this.line + ", column=" + this.column + ")";
    }

    public int getLine() {
        return this.line;
    }

    public Position setLine(int line) {
        this.line = line;
        return this;
    }

    public int getColumn() {
        return this.column;
    }

    public Position setColumn(int column) {
        this.column = column;
        return this;
    }
}
