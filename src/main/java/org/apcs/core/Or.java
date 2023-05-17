package org.apcs.core;

import org.apcs.ast.BoolValue;
import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("or")
public class Or implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) throws EvalException {
        return new BoolValue((boolean) args.get(0).eval(env).value() || (boolean) args.get(1).eval(env).value());
    }
}
