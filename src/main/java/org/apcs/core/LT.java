package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;

import static org.apcs.core.Builtins.cast;

@Define("<")
public class LT implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        return new BoolValue(cast(args.get(0).eval(env), NumberValue.class) < cast(args.get(1).eval(env),
                                                                                   NumberValue.class));
    }
}
