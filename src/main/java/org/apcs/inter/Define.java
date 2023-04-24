package org.apcs.inter;

import org.apcs.ast.Builtin;
import org.apcs.ast.Value;

public class Define implements Builtin {

    @Override
    public Value apply(Environment env, Value... args) {
        env.set((String) args[0].getValue(), args[1]);
        return null;
    }

    @Override
    public Object getValue() {
        return "def";
    }

    @Override
    public Value eval(Environment env) {
        return null;
    }
}
