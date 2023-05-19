package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.ArrayList;
import java.util.List;

import static org.apcs.core.Builtins.cast;

@Define("apply")
public class Apply implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        var list = new ArrayList<>(cast(args.get(1).eval(env), ListValue.class));
        list.add(0, args.get(0));
        return new ListValue(list).eval(env);
    }
}
