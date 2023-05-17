package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("first")
public class First implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) throws EvalException {
        return ((List<Value>) args.get(0).eval(env).value()).get(0);
    }
}
