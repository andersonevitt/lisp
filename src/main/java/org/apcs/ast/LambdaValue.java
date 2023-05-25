package org.apcs.ast;

import java.util.List;

/**
 * Represents an anonymous function or lambda
 * @param args the lambda argument list
 * @param body the lambda body
 */
public record LambdaValue(List<String> args, List<Value<?>> body) implements Value<List<String>> {
    @Override
    public List<String> value() {
        return args;
    }

    /**
     * Returns the string representation of a function.
     * This is formatted as <Lambda adress>
     *
     * @return the function as a string
     */
    public String toString() {
        return "<Lambda " + Integer.toHexString(hashCode()) + ">";
    }
}
