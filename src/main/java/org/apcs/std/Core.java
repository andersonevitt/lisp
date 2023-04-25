package org.apcs.std;

import org.apcs.ast.Lambda;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

public class Core {

    static public Value add(Environment env, List<Value> args) {
        return new NumberValue(((NumberValue) args.get(0).eval(env)).getValue() + ((NumberValue) args.get(1).eval(env)).getValue());
    }

    static public Value def(Environment env, List<Value> args) {
        env.set((String) args.get(0).getValue(), args.get(1).eval(env));
        return null;
    }

    public static Value defun(Environment env, List<Value> args) {
        env.set((String) args.remove(0).getValue(), new Lambda(
                ((List<Value>) args.remove(0).getValue())
                        .stream()
                        .map((v) -> (String) v.getValue()).toList(),
                args));
        return null;
    }

    public static Value lambda(Environment env, List<Value> args) {
        return new Lambda(
                ((List<Value>) args.remove(0).getValue())
                        .stream()
                        .map((v) -> (String) v.getValue()).toList(),
                args);
    }

    public static Value quote(Environment env, List<Value> args) {
        return args.get(0);
    }

    public static Value println(Environment env, List<Value> args) {
        System.out.println(args.get(0).eval(env));

        return null;
    }


}
