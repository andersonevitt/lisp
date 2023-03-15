package org.apcs.eval;

public class EvaluationException extends Exception {
    public EvaluationException() {this(null, null);}

    public EvaluationException(final String message, final Throwable cause) {
        super(message);
        if (cause != null) super.initCause(cause);
    }

    public EvaluationException(final String message) {this(message, null);}

    public EvaluationException(final Throwable cause) {this(cause != null ? cause.getMessage() : null, cause);}
}
