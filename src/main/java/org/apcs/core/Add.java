package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("+")
public class Add implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
        var num = args.stream().map((x) -> (Double) x.eval(env).value())
                .reduce(0.0, Double::sum);

        return new NumberValue(num);
    }
}
