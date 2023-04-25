package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("def")
public class Def implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
        env.set((String) args.get(0).value(), args.get(1).eval(env));
        return ListValue.nil();
    }
}
