package org.apcs.ast;

import java.util.List;

public record Lambda(List<String> args, List<Value> body) implements Value {
    @Override
    public Object value() {
        return args;
    }

    public String toString() {
        return "<Lambda " + Integer.toHexString(hashCode()) + ">";
    }
}
