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

import org.apcs.cli.CLI;
import picocli.CommandLine;

/**
 * The entry point of the interpreter. This parsers the command line arguments, sets the log level, and interpreters
 * whatever is given.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // Set log level
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");

        int exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }
}