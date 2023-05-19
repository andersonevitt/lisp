package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.Builtins.cast;
import static org.apcs.core.Builtins.requireArity;

@Define("first")
public class First implements BuiltinValue {
    /**
     * Returns the first element of a given list
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the first element
     * @throws LispException if the list does not have a first element
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        requireArity(args, 1);
        return cast(args.get(0).eval(env), ListValue.class).get(0);
    }
}
