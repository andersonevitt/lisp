package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;
import java.util.stream.Stream;

import static org.apcs.core.Builtins.cast;
import static org.apcs.core.Builtins.requireArity;

@Define("concat")
public class Concat implements BuiltinValue {
    /**
     * Concatenates a string or a list together
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the evaluated builtin function result
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        requireArity(args, 2);
        var val1 = args.get(0).eval(env);
        var val2 = args.get(1).eval(env);

        if (val1 instanceof StringValue && val2 instanceof StringValue) {
            return new StringValue(cast(val1, StringValue.class) + cast(val2, StringValue.class));
        } else if (val1 instanceof ListValue && val2 instanceof ListValue) {
            return new ListValue(Stream.concat(cast(val1, ListValue.class).stream(),
                                               cast(val2, ListValue.class).stream()).toList());
        } else {
            throw new EvalException("Concat arguments must be a list or string");
        }
    }
}
