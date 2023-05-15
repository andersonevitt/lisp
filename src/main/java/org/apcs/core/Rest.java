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
        return new ListValue(((List<Value>) args.get(0).eval(env).value()).subList(1, args.size()));
    }
}
