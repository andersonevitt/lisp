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

import org.apcs.core.Environment;
import org.apcs.core.EvalException;

import java.util.List;

public record ListValue(List<Value> values) implements Value {
    /**
     * Returns a new list from all of thee arguments
     *
     * @param values the lisp values to construct the list from
     */
    public ListValue(Value... values) {
        this(List.of(values));
    }

    /**
     * Returns a new empty list, also known as nil.
     * This could also be constructed by evaluating '()
     *
     * @return the nil list
     */
    public static ListValue nil() {
        return new ListValue(List.of());
    }

    /**
     * Returns a list as a String.
     * Formats the list with all elements between parenthesis and with space between them.
     * eg. () (1) (1 2 3 4)
     *
     * @return the formatted String
     */
    public String toString() {
        if (values.isEmpty()) {
            return "()";
        }

        var body = new StringBuilder();
        body.append("(");

        for (int i = 0; i < values.size() - 1; i++) {
            body.append(values.get(i)).append(" ");
        }

        body.append(values.get(values.size() - 1));
        body.append(")");

        return body.toString();
    }

    @Override
    public Object value() {
        return values;
    }

    /**
     * Evaluates a list within the given environment.
     * This should call the first item in the list with the rest of the list
     * as the function parameters.
     *
     * @param env The environment in which it should be evaluated
     * @return The evaluated form of the list
     */
    @Override
    public Value eval(Environment env) {
        var newEnv = new Environment(env);

        // Make copy to avoid mutation related bugs
        // TODO: remove unnecessary copy of array for performance reasons?
        var func = values.get(0).eval(env);
        var vals = values.stream().skip(1).toList();

        if (func instanceof Builtin bf) {
            try {
                return bf.apply(env, vals);
            } catch (ClassCastException c) {
                throw new EvalException("Unable to cast");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EvalException("Unable to access array element\n" + e.getMessage());
            }

        } else if (func instanceof Lambda l) {
            assert l.args().size() == vals.size();

            for (int i = 0; i < l.args().size(); i += 1) {
                newEnv.define(l.args().get(i), vals.get(i).eval(env));
            }

            return evalList(l.body(), newEnv);
        } else {
            throw new RuntimeException(func.getClass() + " is not callable: ");
        }
    }

    public Value evalList(List<Value> list, Environment env) {
        // Evaluate all args except last
        for (int i = 0; i < list.size() - 1; i += 1) {
            list.get(i).eval(env);
        }

        // return last argument and evaluate it
        return list.get(list.size() - 1).eval(env);
    }
}
