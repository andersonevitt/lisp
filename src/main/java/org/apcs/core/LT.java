package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BoolValue;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("<")
public class LT implements BuiltinValue {
    @Override
    public Value apply(Environment env, List<Value> args) throws LispException {
        return new BoolValue((Double) args.get(0).value() < (Double) args.get(1).value());
    }
}
