package org.apcs.eval;

import org.apcs.parser.Value;

public interface Evaluator {
    void eval(Value[] values) throws EvaluationException;
}
