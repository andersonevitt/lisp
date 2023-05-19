package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.Builtins.cast;
import static org.apcs.core.Builtins.requireArity;

@Define("rest")
public class Rest implements BuiltinValue {
    /**
     * Returns all except for the first argument in a list
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the new sublist
     * @throws LispException if the array doesn't have more than 1 argument
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        requireArity(args, 1);
        var list = cast(args.get(0).eval(env), ListValue.class);
        return new ListValue(list.subList(1, list.size()));
    }
}
