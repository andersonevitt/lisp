package org.apcs.ast;

import org.apcs.LispException;
import org.apcs.core.Environment;

import java.util.List;

public interface BuiltinValue extends Value {
    /**
     * Call the current builtin with the given environment and arguments
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the evaluated builtin function result
     */
    Value apply(Environment env, List<Value> args) throws LispException;

    /**
     * The name of the builtin as a string.
     * This should not be implemented if the builtin has the @Define annotation
     *
     * @return the name of the function
     */
    default String value() {
        return null;
    }

    default String typeName() {
        throw new IllegalStateException("Not implemented");
    }
}
