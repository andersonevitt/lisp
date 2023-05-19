package org.apcs.core;

import org.apcs.ast.*;

public class CoreUtils {
    private CoreUtils() {}

    /**
     * Casts a given value to the underlying type of the class provided
     *
     * @param value the value to cast
     * @param clazz the Value subclass to cast to
     * @return the cast output
     * @throws TypeException If the types are not convertible
     */
    public static <E, T extends Value<E>> E cast(Value<?> value, Class<T> clazz) throws TypeException {
        try {
            return clazz.cast(value).value();
        } catch (ClassCastException e) {
            throw new TypeException("Cannot cast " + value.typeName() + " to " + getTypeName(clazz));
        }
    }

    /**
     * Returns the name of a given Value class as a lisp type name
     *
     * @return the lisp type name
     */
    public static String getTypeName(Class<? extends Value> clazz) {
        if (clazz.equals(BoolValue.class)) {
            return "boolean";
        } else if (clazz.equals(NumberValue.class)) {
            return "number";
        } else if (clazz.equals(StringValue.class)) {
            return "string";
        } else if (clazz.equals(SymbolValue.class)) {
            return "symbol";
        } else if (clazz.equals(ListValue.class)) {
            return "list";
        } else if (clazz.equals(BuiltinValue.class) || clazz.equals(LambdaValue.class)) {
            return "lambda";
        } else {
            sneakyThrow(new InternalException("Unable to match type name"));
            return null;
        }
    }

    public static <E extends Throwable> void sneakyThrow(Throwable e) throws E {
        throw (E) e;
    }

}
