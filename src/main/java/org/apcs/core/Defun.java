package org.apcs.core;

import com.google.common.collect.Range;
import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;

import static org.apcs.core.Builtins.cast;
import static org.apcs.core.Builtins.requireArity;

/**
 * Defines the given fun in the current environment.
 * eg. (defun f (x) 10) defines 'f' as a function that returns 10
 */
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
