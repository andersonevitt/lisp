package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("apply")
public class Apply implements BuiltinValue {
    @Override
    public Value apply(Environment env, List<Value> args) throws LispException {
        var list = List.copyOf((List<Value>) args.get(1).eval(env).value());
        list.add(0, args.get(0));
        return new ListValue(list).eval(env);
    }
}
