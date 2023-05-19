package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BoolValue;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("and")
public class And implements BuiltinValue {
    @Override
    public Value apply(Environment env, List<Value> args) throws LispException {
        return new BoolValue((boolean) args.get(0).eval(env).value() && (boolean) args.get(1).eval(env).value());
    }
}