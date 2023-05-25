package org.apcs.cli;

import org.apcs.Interpreter;
import org.apcs.lexer.CharacterStream;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Represents the command line arguments for the program
 */
@Command(name = "lisp")
public class CLI implements Runnable {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    private final boolean help = false;

    @Option(names = {"-l", "--log"}, description = "Log interpreter output")
    private final boolean log = false;

    @Parameters(paramLabel = "<file>", description = "lisp files to execute")
    private File[] files;

    /**
     * Starts cli
     */
    @Override
    public void run() {
        var interpreter = new Interpreter();

        for (File f : files) {
            try {
                interpreter.eval(new CharacterStream(f));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
