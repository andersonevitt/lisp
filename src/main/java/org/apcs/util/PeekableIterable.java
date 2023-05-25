package org.apcs.util;

/**
 * An iterable interface that supports looking at the current element without consuming it
 * @param <T></T> the element type
 */
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
