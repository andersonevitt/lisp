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

import lombok.NonNull;
import org.apcs.util.PeekableIterator;

import java.io.*;
import java.nio.file.Path;

public class CharacterStream implements PeekableIterator<Character> {
    @NonNull
    private final BufferedReader reader;
    @NonNull
    private Position position;


    public CharacterStream(BufferedReader reader) {
        this.reader = reader;
        this.position = new Position();
    }

    public CharacterStream(InputStream stream) {
        this.reader = new BufferedReader(new InputStreamReader(stream));
        this.position = new Position();
    }

    public CharacterStream(String source) {
        this.reader = new BufferedReader(new StringReader(source));
        this.position = new Position();
    }

    public CharacterStream(Path input) throws FileNotFoundException {
        this(new File(input.toUri()));
    }

    public CharacterStream(File input) throws FileNotFoundException {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
        this.position = new Position(0, 0);
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
    public Character peek() {
        try {
            reader.mark(1);
            int value = reader.read();
            reader.reset();
            return (char) value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Character next() {
        try {
            char value = (char) reader.read();

            if (value == '\n')
                position.nextLine();
            else if (value == '\r' && peek() == '\n')
                position.nextLine();

            position.nextColumn();
            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public @NonNull BufferedReader getReader() {return this.reader;}

    public @NonNull Position getPosition() {return this.position;}
}
