package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("eval")
public class Eval implements BuiltinValue {
    @Override
    public Value apply(Environment env, List<Value> args) throws LispException {
        return args.get(0).eval(env);
    }
}
