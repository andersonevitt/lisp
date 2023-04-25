package org.apcs.ast;

import org.apcs.std.Environment;

import java.util.List;

public record Lambda(List<String> args, List<Value> body) implements Value {
    @Override
    public Object getValue() {
        return args;
    }

    @Override
    public Value eval(Environment env) {
        return this;
    }
}
