package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("+")
public class Add implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) throws EvalException {
        double start = 0.0;

        for (Value val : args) {
            start += (Double) val.eval(env).value();
        }

        return new NumberValue(start);
    }
}
