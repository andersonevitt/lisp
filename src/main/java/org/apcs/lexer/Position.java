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

import java.util.Objects;

public class Position {
    private String file;
    private int line;
    private int column;

    public Position() {
        this(1, 1);
    }

    public Position(String fileName) {
        this(1, 1);
        file = fileName;
    }

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
        file = null;
    }

    public void nextLine() {
        line += 1;
        column = 1;
    }

    public void nextColumn() {
        column += 1;
    }

    public boolean equals(final Position o) {
        // Object.equals for null safe check
        return this.line == o.line && this.column == o.column && Objects.equals(this.file, o.file);
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public String toString() {
        if (file == null)
            return line + ":" + column;
        else
            return file + ":" + line + column;
    }
}
