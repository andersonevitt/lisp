package org.apcs.core;

import org.apcs.ast.Builtin;
import org.apcs.ast.Define;
import org.apcs.ast.Value;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Environment {
    public Environment parent;
    Map<String, Value> values;

    public Environment(Environment parent) {
        this.parent = parent;
        this.values = new HashMap<>();

        fill();
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

    private void fill() {
        var reflections = new Reflections("org.apcs.core");
        var clazzes = reflections.getSubTypesOf(Builtin.class);

        try {
            for (Class<? extends Builtin> clazz : clazzes) {
                var instance = clazz.getDeclaredConstructor().newInstance();
                var defInstance = clazz.getAnnotation(Define.class);

                // If it has the @Define annotation use that, otherwise use value() for name
                if (defInstance == null) {
                    this.values.put((String) instance.value(), instance);
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