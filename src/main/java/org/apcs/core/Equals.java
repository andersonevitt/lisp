package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BoolValue;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.Value;

import java.util.List;

@Define("=")
public class Equals implements BuiltinValue {
    /**
     * Compares two values for equality
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return true if the two arguments are equal, false otherwise
     * @throws LispException if the arguments fail to evaluate
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        return new BoolValue(args.get(0).eval(env).equals(args.get(1).eval(env)));
    }
}
