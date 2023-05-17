package org.apcs.ast;

import org.apcs.core.Environment;
import org.apcs.core.EvalException;

import java.util.List;

public interface Builtin extends Value {
    /**
     * Call the current builtin with the given environment and arguments
     *
     * @param env  the current environment to use
     * @param args the arguments to the function
     * @return the evaluated builtin function result
     */
    Value apply(Environment env, List<Value> args) throws EvalException;

    /**
     * The name of the builtin as a string.
     * This should not be implemented if the builtin has the @Define annotation
     *
     * @return the name of the function
     */
    default String value() {
        return null;
    }
}
