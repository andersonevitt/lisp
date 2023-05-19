package org.apcs.core;

import com.google.common.collect.Range;
import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;

import static org.apcs.core.Builtins.cast;
import static org.apcs.core.Builtins.requireArity;

@Define("if")
public class If implements BuiltinValue {
    public static Value<?> getOrNil(Environment env, List<Value<?>> vals, int index) throws LispException {
        if (index < vals.size()) {
            return vals.get(index).eval(env);
        } else {
            return ListValue.nil();
        }
    }

    /**
     * Returns the second argument if the first argument evaluates to true, otherwise returns the third
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the evaluated arguments
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        requireArity(args, Range.closed(2, 3));

        if (cast(args.get(0).eval(env), BoolValue.class)) {
            return getOrNil(env, args, 1);
        } else {
            return getOrNil(env, args, 2);
        }
    }
}
