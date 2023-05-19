package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("rest")
public class Rest implements BuiltinValue {
    @Override
    public Value apply(Environment env, List<Value> args) throws LispException {
        var list = (List<Value>) args.get(0).eval(env).value();
        return new ListValue(list.subList(1, list.size()));
    }
}
