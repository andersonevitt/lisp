package org.apcs.ast;

import org.apcs.inter.Environment;

public interface Builtin extends Value {
    Value apply(Environment env, Value... args);

}
