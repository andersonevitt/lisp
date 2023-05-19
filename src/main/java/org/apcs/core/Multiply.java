package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.Builtins.cast;

@Define("*")
public class Multiply implements BuiltinValue {
    /**
     * Multiplies all the given arguments together
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the multiplied arguments
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        double start = 1.0;

        for (Value<?> val : args) {
            start *= cast(val.eval(env), NumberValue.class);
        }
        return new NumberValue(start);
    }
}
