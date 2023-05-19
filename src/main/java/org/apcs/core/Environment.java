package org.apcs.core;

import org.apcs.LispException;
import org.apcs.ast.BuiltinValue;
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

    public Environment() throws InternalException {
        this(null);
        standardEnv();
    }

    // Sets value in current scope
    public void define(String name, Value value) {
        this.values.put(name, value);
    }

    // Finds value then sets it
    public void set(String name, Value value) throws LispException {
        Environment env = this.findEnvironment(name);
        if (env != null && env.values.containsKey(name)) {
            env.values.put(name, value);
        } else {
            throw new EvalException(String.format("Unable to find \"%s\"", name));
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
    public Value get(String name) throws LispException {
        Environment env = this.findEnvironment(name);
        if (env != null && env.values.containsKey(name)) {
            return env.values.get(name);
        } else {
            throw new EvalException(String.format("Unable to find \"%s\"", name));
        }
    }

    private void standardEnv() throws InternalException {
        var reflections = new Reflections("org.apcs.core");
        var classes = reflections.getSubTypesOf(BuiltinValue.class);

        try {
            for (Class<? extends BuiltinValue> clazz : classes) {
                var instance = clazz.getDeclaredConstructor().newInstance();

                this.values.put(instance.getName(), instance);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new InternalException("Fatal error accessing class", e);
        }
    }
}
