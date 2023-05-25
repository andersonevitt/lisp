package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BoolValue;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.Builtins.cast;

/**
 * returns a new BoolValue that is the boolean and of the given args.
 */
@Define("and")
public class And implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        return new BoolValue(cast(args.get(0).eval(env), BoolValue.class) && cast(args.get(1).eval(env),
                                                                                  BoolValue.class));
    }
}