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

import java.io.*;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CharacterStream implements Iterator<Character> {
    private final Reader reader;
    private final Position position;

    public CharacterStream(Reader reader, Position position) {
        this.reader = new BufferedReader(reader);
        this.position = position;
    }

    public CharacterStream(Reader reader) {
        this(reader, new Position());
    }

    public CharacterStream(InputStream stream, Position position) {
        this(new InputStreamReader(stream), position);
    }

    public CharacterStream(InputStream stream) {
        this(new InputStreamReader(stream));
    }

    public CharacterStream(String source) {
        this(new StringReader(source));
    }

    public CharacterStream(Path input) throws FileNotFoundException {
        this(new File(input.toUri()));
    }

    public CharacterStream(File input) throws FileNotFoundException {
        this(new FileInputStream(input), new Position(input.getName()));
    }

    @Override
    public boolean hasNext() {
        try {
            reader.mark(1);
            int value = reader.read();

            if (value == -1) {
                return false;
            }

            reader.reset();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Character next() throws NoSuchElementException {
        try {
            char value = (char) reader.read();

            // TODO: Multi platform line endings
            if (value == '\n')
                position.nextLine();

            position.nextColumn();
            return value;
        } catch (IOException e) {
            throw new NoSuchElementException();
        }
    }

    public Reader getReader() {
        return reader;
    }

    public Position getPosition() {
        return position;
    }
}
