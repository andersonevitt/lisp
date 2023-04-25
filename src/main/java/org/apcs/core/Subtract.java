package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("-")
public class Subtract implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
        if (args.size() == 1) {
            return new NumberValue(-(double) args.get(0).value());
        }

        var num = (Double) args.get(0).value() - args.stream().skip(1).map((x) -> (Double) x.eval(env).value())
                .reduce(0.0, Double::sum);

        return new NumberValue(num);
    }
}