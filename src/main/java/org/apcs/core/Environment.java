package org.apcs.core;

import org.apcs.ast.BuiltinValue;
import org.apcs.ast.Define;
import org.apcs.ast.Value;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

// TODO: Write documentation and make fields private

public class Environment {
    public final Environment parent;
    private final Map<String, Value> values;

    // Constructor with 1-arg for parent. Creates a new HashMap.
    public Environment(Environment parent) {
        this.parent = parent;
        this.values = new HashMap<>();
    }

    public Environment() {
        this(null);
        standardEnv();
    }

    // Sets value in current scope
    public void define(String name, Value value) {
        this.values.put(name, value);
    }

    // Finds value then sets it
    public void set(String name, Value value) {
        Environment env = this.findEnvironment(name);
        if (env != null && env.values.containsKey(name)) {
            env.values.put(name, value);
        } else {
            throw new RuntimeException(String.format("Unable to find \"%s\"", name));
        }
    }

    Environment findEnvironment(String name) {
        Environment env = this;
        while (env != null && !env.values.containsKey(name)) {
            env = env.parent;
        }
        return env;
    }

    //
    public Value get(String name) {
        Environment env = this.findEnvironment(name);
        if (env != null && env.values.containsKey(name)) {
            return env.values.get(name);
        } else {
            throw new RuntimeException(String.format("Unable to find \"%s\"", name));
        }
    }

    private void standardEnv() {
        var reflections = new Reflections("org.apcs.core");
        var clazzes = reflections.getSubTypesOf(BuiltinValue.class);

        try {
            for (Class<? extends BuiltinValue> clazz : clazzes) {
                var instance = clazz.getDeclaredConstructor().newInstance();
                var defInstance = clazz.getAnnotation(Define.class);

                // If it has the @Define annotation use that, otherwise use value() for name
                if (defInstance == null) {
                    this.values.put(instance.value(), instance);
                } else {
                    this.values.put(defInstance.value(), instance);
                }
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
