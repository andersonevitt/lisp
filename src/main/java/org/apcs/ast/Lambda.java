package org.apcs.ast;

import java.util.List;

public record Lambda(List<String> args, List<Value> body) implements Value {
    @Override
    public Object value() {
        return args;
    }

    /**
     * Returns the string representation of a function.
     * This is in the form <Lambda #adress>
     *
     * @return the function as a string
     */
    public String toString() {
        return "<Lambda " + Integer.toHexString(hashCode()) + ">";
    }
}
