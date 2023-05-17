package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("-")
public class Subtract implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) throws EvalException {
        if (args.size() == 1)
            return new NumberValue(-(double) args.get(0).value());

        double start = (double) args.get(0).eval(env).value();

        for (int i = 1; i < args.size(); i += 1) {
            start -= (double) args.get(i).eval(env).value();
        }

        return new NumberValue(start);
    }
}