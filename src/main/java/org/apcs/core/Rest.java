package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("rest")
public class Rest implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
        var list = (List<Value>) args.get(0).eval(env).value();
        return new ListValue(list.subList(1, list.size()));
    }
}
