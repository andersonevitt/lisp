package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.*;

import java.util.List;

import static org.apcs.core.CoreUtils.cast;
import static org.apcs.core.CoreUtils.requireArity;

@Define("def")
public class Def implements BuiltinValue {
    @Override
    public Value<?> apply(Environment env, List<Value<?>> args) throws LispException {
        requireArity(args, 2);

        env.define(cast(args.get(0), SymbolValue.class), args.get(1).eval(env));
        return ListValue.nil();
    }
}
