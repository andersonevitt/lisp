package org.apcs.core;

import com.google.common.collect.Range;

/**
 * Represents an error when the given and expected number of arguments differ
 */
public class ArityException extends EvalException {
    /**
     * Constructs a new function arity exception.
     * This should be used when the number of arguments for a function call do not match the provided.
     *
     * @param expected expected number of args
     * @param provided found number of args
     */
    public ArityException(Range<Integer> expected, int provided) {
        super(formatArgs(expected, provided));
    }

    public ArityException(int expected, int found) {
        this(Range.singleton(expected), found);
    }

    /**
     * Formats the error message and range correctly
     */
    private static String formatArgs(Range<Integer> expected, int found) {
        var range = "";

        if (expected.hasLowerBound()
                && expected.hasUpperBound()
                && expected.lowerEndpoint().equals(expected.upperEndpoint())) {
            range = expected.lowerEndpoint() + " argument";
        } else if (expected.hasLowerBound() && !expected.hasUpperBound()) {
            range = "at least " + (expected.lowerEndpoint() + 1) + " arguments";
        } else if (!expected.hasLowerBound() && expected.hasUpperBound()) {
            range = "less than " + (expected.upperEndpoint() + 1) + " arguments";
        } else {
            range = "between " + expected.lowerEndpoint() + " and " + expected.upperEndpoint() + " arguments";
        }

        return String.format("""
                                     Function call has an incorrect number of arguments;
                                     Expected %s but provided %d.
                                     """, range, found);
    }
}
