package org.apcs.inter;

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
        this.values.put("+", new Add());
        this.values.put("def", new Define());

    }
}
