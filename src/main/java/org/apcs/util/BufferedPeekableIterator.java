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

public class BufferedPeekableIterator<E> implements PeekableIterator<E> {
    private final Iterator<? extends E> iterator;
    private boolean hasPeeked;
    private E peekedElement;

    /**
     * Constructs an instance from an iterator over a collection
     *
     * @param iterator the iterator to iterate peekably over
     */
    public BufferedPeekableIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    /**
     * Returns whether the element has another item
     *
     * @return whether the element has more items
     */
    @Override
    public boolean hasNext() {
        return hasPeeked || iterator.hasNext();
    }

    /**
     * Get the next item from the iterator and advance the underlying iterator
     *
     * @return the next item from the iterator
     */
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

    /**
     * Peek the next item in the collection with advancing the iterator or consuming the item.
     * peek() can be called multiple times and will return the same result until next() is called.
     *
     * @return the current item in the iterator
     */
    @Override
    public E peek() {
        if (!hasPeeked) {
            peekedElement = iterator.next();
            hasPeeked = true;
        }

        return peekedElement;
    }
}