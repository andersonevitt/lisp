package org.apcs.ast;

import java.util.List;

public record LambdaValue(List<String> args, List<Value<?>> body) implements Value<List<String>> {
    @Override
    public List<String> value() {
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
