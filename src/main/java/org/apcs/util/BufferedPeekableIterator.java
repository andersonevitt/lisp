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

package org.apcs.util;

import lombok.Getter;
import lombok.NonNull;

import java.util.Iterator;

public class BufferedPeekableIterator<E> implements PeekableIterator<E> {

    @NonNull
    @Getter
    private final Iterator<? extends E> iterator;
    @Getter
    private boolean hasPeeked;
    @Getter
    private E peekedElement;

    public BufferedPeekableIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return hasPeeked || iterator.hasNext();
    }

    @Override
    public E next() {
        if (!hasPeeked) {
            return iterator.next();
        }

        E result = peekedElement;
        hasPeeked = false;
        peekedElement = null;
        return result;
    }


    @Override
    public E peek() {
        if (!hasPeeked) {
            peekedElement = iterator.next();
            hasPeeked = true;
        }

        return peekedElement;
    }
}