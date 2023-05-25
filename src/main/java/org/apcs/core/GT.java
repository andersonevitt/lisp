package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;

import static org.apcs.core.Builtins.cast;
import static org.apcs.core.Builtins.requireArity;

@Define(">")
public class GT implements BuiltinValue {
    /**
     * Returns true if the first argument is greater than the second argument
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return whether the first argument is greater than the second
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        // TODO: add ability for more args
        requireArity(args, 2);
        return new BoolValue(cast(args.get(0).eval(env), NumberValue.class) > cast(args.get(1).eval(env),
                                                                                   NumberValue.class));
    }
}
