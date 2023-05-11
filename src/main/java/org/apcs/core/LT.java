package org.apcs.core;

import org.apcs.ast.BoolValue;
import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("<")
public class LT implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
        return new BoolValue((Double) args.get(0).value() < (Double) args.get(1).value());
    }
}
