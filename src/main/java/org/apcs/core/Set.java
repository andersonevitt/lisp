package org.apcs.core;

import org.apcs.ast.*;

import java.util.List;

@Define("set!")
public class Set implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
        env.set((String) args.get(0).value(), args.get(1).eval(env));
        return ListValue.nil();
    }
}