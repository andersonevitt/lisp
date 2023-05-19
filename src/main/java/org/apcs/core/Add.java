package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.Builtins.cast;

@Define("+")
public class Add implements BuiltinValue {
    /** Starts at 0.0 and adds other input to double start
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the sum of start (0.0) and other numbers being added
     * @throws LispException
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        double start = 0.0;

        for (Value<?> val : args) {
            start += cast(val.eval(env), NumberValue.class);
        }

        return new NumberValue(start);
    }
}
