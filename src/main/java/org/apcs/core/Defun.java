package org.apcs.core;

import org.apcs.ast.*;
import org.apcs.ast.Lambda;

import java.util.List;

@Define("defun")
public class Defun implements Builtin {
    @Override
    public Value apply(Environment env, List<Value> args) {
                env.define((String) args.remove(0).value(), new Lambda(
                ((List<Value>) args.remove(0).value())
                        .stream()
                        .map((v) -> (String) v.value()).toList(), args));
        return ListValue.nil();
    }
}
