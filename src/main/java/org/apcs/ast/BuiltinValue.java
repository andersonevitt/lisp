package org.apcs.ast;

import org.apcs.LispException;
import org.apcs.core.Environment;

import java.util.List;

public non-sealed interface BuiltinValue extends Value<String> {
    /**
     * Call the current builtin with the given environment and arguments
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the evaluated builtin function result
     */
    Value<?> apply(Environment env, List<Value<?>> args) throws LispException;

    /**
     * The name of the builtin as a str
     * This should not be implemented if the builtin has the @Define annotation
     *
     * @return the name of the function
     */
    default String value() {
        return null;
    }

    @Override
    default String typeName() {
        return "lambda";
    }

    /**
     * Gets the name of the builtin function from either its annotation or value()
     *
     * @return the name of the builtin
     */
    default String getName() {
        try {
            var defInstance = this.getClass().getAnnotation(Define.class);

            // If it is annotated with the @Define annotation use that, otherwise use value() for name
            if (defInstance == null) {
                return value();
            } else {
                return defInstance.value();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
