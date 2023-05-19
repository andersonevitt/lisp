package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.CoreUtils.cast;

@Define("/")
public class Divide implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        double start = cast(args.get(0).eval(env), NumberValue.class);

        for (int i = 1; i < args.size(); i += 1) {
            start /= cast(args.get(i).eval(env), NumberValue.class);
        }

        return new NumberValue(start);
    }
}