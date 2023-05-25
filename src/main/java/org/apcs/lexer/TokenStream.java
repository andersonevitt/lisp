package org.apcs.lexer;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface TokenStream extends Iterator<Token> {
    /**
     * Returns true or false in the instance
     * that the iterator contains a value
     *
     * @return whether the next space is full or empty
     */
    @Override
    boolean hasNext();

    /**
     * Returns the current token from the lexer and advances it
     *
     * @return the current token
     */
    @Override
    Token next() throws NoSuchElementException;

    /**
     * Returns the position of lexer
     *
     * @return the line and column information
     */
    Position getPosition();
}
