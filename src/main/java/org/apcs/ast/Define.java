package org.apcs.ast;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Define {
    /**
     * The name of the builtin. This will define the method in the environment for you
     *
     * @return the name of the function
     */
    String value();
}
