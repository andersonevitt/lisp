package org.apcs.std;

import org.apcs.ast.Lambda;
import org.apcs.ast.ListValue;
import org.apcs.ast.NumberValue;
import org.apcs.ast.Value;

import java.util.List;

public class Core {

    static public Value add(Environment env, List<Value> args) {
        return new NumberValue(((NumberValue) args.get(0).eval(env)).value() + ((NumberValue) args.get(1).eval(env)).value());
    }

    static public Value subtract(Environment env, List<Value> args) {
        return new NumberValue(((NumberValue) args.get(0).eval(env)).value() - ((NumberValue) args.get(1).eval(env)).value());
    }

    static public Value multiply(Environment env, List<Value> args) {
        return new NumberValue(((NumberValue) args.get(0).eval(env)).value() * ((NumberValue) args.get(1).eval(env)).value());
    }

    static public Value divide(Environment env, List<Value> args) {
        return new NumberValue(((NumberValue) args.get(0).eval(env)).value() / ((NumberValue) args.get(1).eval(env)).value());
    }


    static public Value def(Environment env, List<Value> args) {
        env.set((String) args.get(0).value(), args.get(1).eval(env));
        return ListValue.nil();
    }

    public static Value defun(Environment env, List<Value> args) {
        env.set((String) args.remove(0).value(), new Lambda(
                ((List<Value>) args.remove(0).value())
                        .stream()
                        .map((v) -> (String) v.value()).toList(),
                args));
        return ListValue.nil();
    }

    public static Value lambda(Environment env, List<Value> args) {
        return new Lambda(
                ((List<Value>) args.remove(0).value())
                        .stream()
                        .map((v) -> (String) v.value()).toList(),
                args);
    }

    public static Value quote(Environment env, List<Value> args) {
        return args.get(0);
    }

    public static Value println(Environment env, List<Value> args) {
        System.out.println(args.get(0).eval(env));
        return ListValue.nil();
    }


}
