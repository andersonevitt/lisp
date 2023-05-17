package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("println")
public class Println implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) throws EvalException {
        System.out.println(args.get(0).eval(env));
        return ListValue.nil();
    }
}
