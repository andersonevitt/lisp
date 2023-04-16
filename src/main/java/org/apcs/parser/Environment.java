package org.apcs.parser;

import org.apcs.ast.Value;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    Map<String, Value> values;
    public Environment parent;

    public Environment(Environment parent) {
        this.parent = parent;
        this.values = new HashMap<>();
    }

    public void set(String name, Value value) {
        Environment env = this.findEnvironment(name);
        if (env != null) {
            env.values.put(name, value);
        } else {
            this.values.put(name, value);
        }
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
            return null;
        }
    }
}
