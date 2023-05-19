package org.apcs.core;

import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("quote")
public class Quote implements BuiltinValue {
    /**
     * Returns the quoted version of the argument.
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the quoted form of the argument
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) {
        return args.get(0);
    }
}