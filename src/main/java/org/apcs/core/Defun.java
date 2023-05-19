package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;

@Define("defun")
public class Defun implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        env.define((String) args.remove(0).value(), new LambdaValue(
                ((List<Value<?>>) args.remove(0).value())
                        .stream()
                        .map(v -> (String) v.value()).toList(), args));
        return ListValue.nil();
    }
}
