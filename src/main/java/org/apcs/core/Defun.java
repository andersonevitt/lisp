package org.apcs.core;

import com.google.common.collect.Range;
import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;

import static org.apcs.core.Builtins.cast;
import static org.apcs.core.Builtins.requireArity;

@Define("defun")
public class Defun implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        requireArity(args, Range.atLeast(3));
        env.define(cast(args.remove(0), SymbolValue.class), new LambdaValue(
                (cast(args.remove(0), ListValue.class))
                        .stream()
                        .map(v -> (String) v.value()).toList(), args));
        return ListValue.nil();
    }
}
