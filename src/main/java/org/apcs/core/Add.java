package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.CoreUtils.cast;

@Define("+")
public class Add implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        double start = 0.0;

        for (Value<?> val : args) {
            start += cast(val.eval(env), NumberValue.class);
        }

        return new NumberValue(start);
    }
}
