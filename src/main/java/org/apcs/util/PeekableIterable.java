package org.apcs.util;

public interface PeekableIterable<T> extends Iterable<T> {

    default PeekableIterator<T> peekableIterator() {
        return PeekableIterator.of(this);
    }
}
