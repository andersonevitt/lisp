package org.apcs.core;

import com.google.common.collect.Range;
import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

import static org.apcs.core.Builtins.cast;
import static org.apcs.core.Builtins.requireArity;

@Define("/")
public class Divide implements BuiltinValue {
    /**
     * Divides the given arguments
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the quotient of the numbers
     * @throws LispException
     */
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        requireArity(args, Range.greaterThan(1));
        double start = cast(args.get(0).eval(env), NumberValue.class);

        for (int i = 1; i < args.size(); i += 1) {
            start /= cast(args.get(i).eval(env), NumberValue.class);
        }

        return new NumberValue(start);
    }
}