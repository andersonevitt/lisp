package org.apcs.eval;

import org.apcs.parser.Value;

import java.io.FileOutputStream;

public class JVMCompiler implements Evaluator {
    private final FileOutputStream output;
    Object clazz;

    public JVMCompiler(FileOutputStream output) {
        this.output = output;
    }

    @Override
    public void eval(Value[] values) {

    }
}
