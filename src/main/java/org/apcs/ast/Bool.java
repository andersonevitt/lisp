package org.apcs.ast;

import org.apcs.core.Environment;

public record Bool(Boolean value) implements Value {
    @Override
    public Value eval(Environment env) {
        return this;
    }
}
