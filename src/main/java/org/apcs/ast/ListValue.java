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

import java.util.ArrayList;
import java.util.List;

public record ListValue(List<Value> values) implements Value {
    public ListValue(Value... values) {
        this(List.of(values));
    }

    public static ListValue nil() {
        return new ListValue(List.of());
    }

    public String toString() {
        if (values.size() == 0) {
            return "()";
        }

        StringBuilder body = new StringBuilder();
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

    @Override
    public Value eval(Environment env) {
        var newEnv = new Environment(env);

        // Make copy to avoid mutation related bugs
        // TODO: remove unnecessary copy of array for performance reasons?
        var func = values.get(0).eval(env);
        var vals = new ArrayList<>(values.stream().skip(1).toList());

        if (func instanceof Builtin bf) {
            return bf.apply(env, vals);
        } else if (func instanceof Lambda l) {
            assert l.args().size() == vals.size();

            for (int i = 0; i < l.args().size(); i += 1) {
                newEnv.define(l.args().get(i), vals.get(i).eval(env));
            }

            return evalList(l.body(), newEnv);
        } else {
            throw new RuntimeException(func.getClass() + " not callable: ");
        }
    }

    public Value evalList(List<Value> list, Environment env) {
        for (int i = 0; i < list.size() - 1; i += 1) {
            list.get(i).eval(env);
        }

        return list.get(list.size() - 1).eval(env);
    }
}
