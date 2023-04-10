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

package org.apcs;

import org.apcs.lexer.CharacterStream;
import org.apcs.lexer.Lexer;

public class Main {
    public static void main(String[] args) throws Exception {
        var lexer = new Lexer(new CharacterStream("(+ some 20)"));
        lexer.forEachRemaining(System.out::println);

//
//        var parser = new Parser(lexer);
//
//        parser.forEachRemaining(System.out::println);
    }
}