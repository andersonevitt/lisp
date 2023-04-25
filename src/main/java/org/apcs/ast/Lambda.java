package org.apcs.ast;

import org.apcs.core.Environment;

import java.util.List;

public record Lambda(List<String> args, List<Value> body) implements Value {
    @Override
    public Object value() {
        return args;
    }

    @Override
    public Value eval(Environment env) {
        return this;
    }

    public String toString() {
        return "<Lambda " + Integer.toHexString(hashCode()) + ">";
    }
}
