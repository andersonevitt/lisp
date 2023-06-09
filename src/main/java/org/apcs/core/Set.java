package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;

import static org.apcs.core.Builtins.cast;
import static org.apcs.core.Builtins.requireArity;

/**
 * Sets the first argument to the second one. This method finds wherever it is defined and modifies that instead of
 * defining it in the current environment.
 */
@Define("set!")
public class Set implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        requireArity(args, 2);
        env.set(cast(args.get(0), SymbolValue.class), args.get(1).eval(env));
        return ListValue.nil();
    }
}