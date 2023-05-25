package org.apcs.ast;

/**
 * A boolean lisp value
 */
public record BoolValue(Boolean value) implements Value<Boolean> {}