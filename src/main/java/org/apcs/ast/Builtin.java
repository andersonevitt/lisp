package org.apcs.ast;

import org.apcs.std.Environment;

import java.util.List;

public interface Builtin extends Value {
    Value apply(Environment env, List<Value> args);

    default Object value() {
        return null;
    }

    default Value eval(Environment env) {
        return null;
    }

}
