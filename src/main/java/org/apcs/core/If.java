package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.ListValue;
import org.apcs.ast.Value;

import java.util.List;

@Define("if")
public class If implements BuiltinValue {
    public static Value<?> getOrNil(Environment env, List<Value<?>> vals, int index) throws EvalException {
        if (index < vals.size()) {
            try {
                return vals.get(index).eval(env);
            } catch (LispException e) {
                throw new RuntimeException(e);
            }
        } else {
            return ListValue.nil();
        }
    }

    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        if ((boolean) args.get(0).eval(env).value()) {
            return getOrNil(env, args, 1);
        } else {
            return getOrNil(env, args, 2);
        }
    }
}
