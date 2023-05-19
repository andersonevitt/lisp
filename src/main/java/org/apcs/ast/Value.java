/*
 * Copyright (C) 2023 Anderson Evitt, Zoe Schauder, Carly Linquist
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.apcs.ast;

import org.apcs.LispException;
import org.apcs.core.CoreUtils;
import org.apcs.core.Environment;

/**
 * A representation of a lisp value
 *
 * @param <T> the underlying type of the value that is retrieved with value()
 */
public interface Value<T> {
    /**
     * Returns the Java representation of the Value
     *
     * @return the Java representation of the Value
     */
    T value();

    /**
     * Returns the name of the lisp type
     *
     * @return the name of the lisp value
     */
    default String typeName() {
        return CoreUtils.getTypeName(getClass());
    }

    /**
     * Returns the evaluated form of the value.
     * By default, it should not do anything and simply evaluate to itself
     *
     * @param env The environment in which it should be evaluated
     * @return the evaluated form
     */
    default Value<?> eval(Environment env) throws LispException {
        return this;
    }
}