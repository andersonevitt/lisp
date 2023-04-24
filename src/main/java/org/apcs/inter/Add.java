package org.apcs.inter;

import org.apcs.ast.Builtin;
import org.apcs.ast.Number;
import org.apcs.ast.Value;

public class Add implements Builtin {
    @Override
    public Value apply(Environment env, Value... args) {
        return new Number(((Number) (args[0]).eval(env)).getValue() + ((Number) (args[1]).eval(env)).getValue());
    }

    @Override
    public Object getValue() {
        return "+";
    }

    @Override
    public Value eval(Environment env) {
        return null;
    }
}
