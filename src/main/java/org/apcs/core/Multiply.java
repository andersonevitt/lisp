package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("*")
public class Multiply implements BuiltinValue {
    @Override
    public Value apply(Environment env, List<Value> args) throws LispException {
        double start = 1.0;

        for (Value val : args) {
            start *= (Double) val.eval(env).value();
        }
        return new NumberValue(start);
    }
}
