package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("lambda")
public class Lambda implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
        env.set((String) args.remove(0).value(), new org.apcs.ast.Lambda(
                ((List<Value>) args.remove(0).value())
                        .stream()
                        .map((v) -> (String) v.value()).toList(),
                args));
        return ListValue.nil();
    }
}