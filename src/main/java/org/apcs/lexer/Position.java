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
    private final String file;
    private int line;
    private int column;

    public Position() {
        this(1, 1);
    }

    public Position(String fileName) {
        this(1, 1, fileName);
    }

    /**
     * constructor in the case where file is not null
     *
     * @param line The line number
     * @param column The column number
     * @param fileName The name of the file
     */
    public Position(int line, int column, String fileName) {
        this.line = line;
        this.column = column;
        this.file = fileName;
    }

    /**
     * constructor in the case that the file is null
     *
     * @param line
     * @param column
     */
    public Position(int line, int column) {
        this(line, column, null);
    }

    /**
     * method moves position to the next line
     */
    public void nextLine() {
        line += 1;
        column = 1;
    }

    /**
     * method moves column position forward
     */
    public void nextColumn() {
        column += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (getLine() != position.getLine()) return false;
        if (getColumn() != position.getColumn()) return false;
        return Objects.equals(file, position.file);
    }

    @Override
    public int hashCode() {
        int result = file != null ? file.hashCode() : 0;
        result = 31 * result + getLine();
        result = 31 * result + getColumn();
        return result;
    }

    /**
     * Returns the integer representing the position of the current line.
     *
     * @return position of line
     */
    public int getLine() {
        return this.line;
    }

    /**
     * Returns the integer representing the position of the current column.
     *
     * @return position of column
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Returns the line and column if the file is null.
     * If the file is null, the file is returned in addition
     * to the line and column
     *
     * @return the value of str
     */
    @Override
    public String toString() {
        var str = line + ":" + column;

        if (file != null) str = file + ":" + str;

        return str;
    }
}
