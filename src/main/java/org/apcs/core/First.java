package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.CoreUtils.cast;

@Define("first")
public class First implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        return (cast(args.get(0).eval(env), ListValue.class)).get(0);
    }
}
