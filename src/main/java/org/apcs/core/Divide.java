package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("/")
public class Divide implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
        return new NumberValue(((NumberValue) args.get(0).eval(env)).value() / ((NumberValue) args.get(1).eval(env)).value());

    }
}