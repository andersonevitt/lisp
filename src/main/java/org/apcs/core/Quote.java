package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("quote")
public class Quote implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) throws EvalException {
        return args.get(0);
    }
}