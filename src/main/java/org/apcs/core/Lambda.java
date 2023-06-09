package org.apcs.core;

import com.google.common.collect.Range;
import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;

import static org.apcs.core.Builtins.cast;
import static org.apcs.core.Builtins.requireArity;

/**
 * Creates a lambda with the given args. The first arg is the arguments, and all the rest of the arguments are the
 * body.
 */
@Define("lambda")
public class Lambda implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        requireArity(args, Range.greaterThan(2));

        return new LambdaValue(
                cast(args.remove(0), ListValue.class)
                        .stream()
                        .map(v -> (String) v.value()).toList(),
                args);
    }
}
