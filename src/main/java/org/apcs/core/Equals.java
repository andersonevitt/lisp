package org.apcs.core;

import org.apcs.ast.BoolValue;
import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("=")
public class Equals implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
        return new BoolValue(args.get(0).eval(env).equals(args.get(1).eval(env)));
    }
}
