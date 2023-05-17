package org.apcs.ast;

public record BoolValue(Boolean value) implements Value {
    /**
     * Returns the name of the lisp type
     *
     * @return the name of the lisp value
     */
    public String typeName() {
        return "bool";
    }
}