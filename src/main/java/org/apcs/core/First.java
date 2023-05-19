package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("first")
public class First implements BuiltinValue {
    @Override
    public Value apply(Environment env, List<Value> args) throws LispException {
        return ((List<Value>) args.get(0).eval(env).value()).get(0);
    }
}
