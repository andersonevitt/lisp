package org.apcs.util;

public interface PeekableIterable<T> extends Iterable<T> {

    /**
     * Returns the peekable iterator over the implementing collection
     *
     * @return a peekable iterator
     */
    default PeekableIterator<T> peekableIterator() {
        return PeekableIterator.of(this);
    }
}
