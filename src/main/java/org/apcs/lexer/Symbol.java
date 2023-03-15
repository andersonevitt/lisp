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

package org.apcs.lexer;

import lombok.NonNull;

public final class Symbol extends Token {
    @NonNull
    private final String value;

    public Symbol(@NonNull String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ATOM(" + value + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Symbol)) return false;
        final Symbol other = (Symbol) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$value = this.value;
        final Object other$value = other.value;
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {return other instanceof Symbol;}

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $value = this.value;
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public @NonNull String getValue() {return this.value;}
}
