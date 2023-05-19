package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.LambdaValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("lambda")
public class Lambda implements BuiltinValue {
    @Override
    public Value apply(Environment env, List<Value> args) throws LispException {
        return new LambdaValue(
                ((List<Value>) args.remove(0).value())
                        .stream()
                        .map(v -> (String) v.value()).toList(),
                args);
    }
}
