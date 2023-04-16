package org.apcs.ast;

import org.apcs.parser.Environment;

import java.util.List;

@lombok.Value
public class Lambda implements Value {
    List<String> args;
    List<Value> body;


    @Override
    public Object getValue() {
        return args;
    }

    @Override
    public Value eval(Environment env) {
        return null;
    }
}
