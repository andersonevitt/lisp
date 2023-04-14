package org.apcs.parser;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    Map<String, Value> values;
    Environment parent;

    Environment(Environment parent) {
        this.parent = parent;
        this.values = new HashMap<>();
    }

    void set(String name, Value value) {
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

    Value get(String name) {
        Environment env = this.findEnvironment(name);
        if (env != null && env.values.containsKey(name)) {
            return env.values.get(name);
        } else {
            return null;
        }
    }
}
