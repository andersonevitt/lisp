package org.apcs.std;

import org.apcs.ast.Builtin;
import org.apcs.ast.Value;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    public Environment parent;
    Map<String, Value> values;

    public Environment(Environment parent) {
        this.parent = parent;
        this.values = new HashMap<>();

        standardEnv();
    }

    public Environment() {
        this(null);
    }

    public void set(String name, Value value) {
        this.values.put(name, value);
    }

    Environment findEnvironment(String name) {
        Environment env = this;
        while (env != null && !env.values.containsKey(name)) {
            env = env.parent;
        }
        return env;
    }

    public Value get(String name) {
        Environment env = this.findEnvironment(name);
        if (env != null && env.values.containsKey(name)) {
            return env.values.get(name);
        } else {
            throw new RuntimeException(String.format("Unable to find \"%s\"", name));
        }
    }


    private void standardEnv() {
        this.values.put("+", (Builtin) Core::add);
        this.values.put("-", (Builtin) Core::subtract);
        this.values.put("*", (Builtin) Core::multiply);
        this.values.put("/", (Builtin) Core::divide);

        this.values.put("def", (Builtin) Core::def);
        this.values.put("defun", (Builtin) Core::defun);
        this.values.put("quote", (Builtin) Core::quote);
        this.values.put("println", (Builtin) Core::println);
        this.values.put("lambda", (Builtin) Core::lambda);
    }
}
