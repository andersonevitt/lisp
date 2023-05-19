package org.apcs.core;

import org.apcs.ast.BoolValue;
import org.apcs.ast.NumberValue;
import org.apcs.ast.StringValue;
import org.apcs.ast.Value;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class CoreUtils {
    private CoreUtils() {
    }

    public static <T> T cast(Value value, Class<T> clazz) throws TypeException {
        try {
            return clazz.cast(value.value());
        } catch (ClassCastException e) {
            throw new TypeException("Cannot cast " + value.typeName() + " to " + clazz.getSimpleName());
        }
    }

    /**
     * Returns the name of a java type as a lisp value type
     *
     * @param object the
     * @return the lisp type name
     */
    public static Optional<Class<? extends Value>> getType(Class<?> object) {
        if (object.equals(Double.class))
            return Optional.of(NumberValue.class);
        else if (object.equals(String.class))
            return Optional.of(StringValue.class);
        else if (object.equals(Boolean.class))
            return Optional.of(BoolValue.class);
        else
            return Optional.empty();
    }

    /**
     * Returns the name of a java type as a lisp value type name
     *
     * @param object the
     * @return the lisp type name
     */
    public static Optional<String> getTypeName(Class<?> object) {
        return getType(object).map((s) -> {
            try {
                var instance = s.getDeclaredConstructor().newInstance();
                return instance.typeName();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
