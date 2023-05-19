package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;

import static org.apcs.core.CoreUtils.cast;

@Define("defun")
public class Defun implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        env.define(cast(args.remove(0), SymbolValue.class), new LambdaValue(
                (cast(args.remove(0), ListValue.class))
                        .stream()
                        .map(v -> (String) v.value()).toList(), args));
        return ListValue.nil();
    }
}
