package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.CoreUtils.*;

@Define("-")
public class Subtract implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        if (args.size() == 1)
            return new NumberValue(-(double) args.get(0).value());

        double start = cast(args.get(0).eval(env), NumberValue.class);

        for (int i = 1; i < args.size(); i += 1) {
            start -= (double) args.get(i).eval(env).value();
        }

        return new NumberValue(start);
    }
}