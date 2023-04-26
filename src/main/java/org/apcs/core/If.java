package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("if")
public class If implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
        if ((boolean) args.get(0).eval(env).value()) {
            return getOrNil(env, args, 1);
        } else {
            return getOrNil(env, args, 2);
        }
    }

    public static Value getOrNil(Environment env, List<Value> vals, int index) {
        if (index < vals.size()) {
            return vals.get(index).eval(env);
        } else {
            return ListValue.nil();
        }
    }
}
