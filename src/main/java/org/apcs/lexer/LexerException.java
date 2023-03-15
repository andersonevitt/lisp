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

public class LexerException extends RuntimeException {
    public LexerException() {this(null, null);}

    public LexerException(final String message) {this(message, null);}

    public LexerException(final Throwable cause) {this(cause != null ? cause.getMessage() : null, cause);}

    public LexerException(final String message, final Throwable cause) {
        super(message);
        if (cause != null) super.initCause(cause);
    }
}
