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

import java.util.Iterator;

public interface PeekableIterator<E> extends Iterator<E> {
    /**
     * Returns a new peekable iterator from an iterator
     *
     * @param value the iterator to be made peekable
     * @return the new peekable iterator over the iterator
     */
    static <T> PeekableIterator<T> of(Iterator<T> value) {
        return new BufferedPeekableIterator<>(value);
    }

    /**
     * Returns a new peekable iterator from an iterable
     *
     * @param value the iterable to be made peekable
     * @return the new peekable iterator over the iterable
     */
    static <T> PeekableIterator<T> of(Iterable<T> value) {
        return new BufferedPeekableIterator<>(value.iterator());
    }

    /**
     * Peek the next item in the collection with advancing the iterator or consuming the item.
     * peek() can be called multiple times and will return the same result until next() is called.
     *
     * @return the current item in the iterator
     */
    E peek();
}
