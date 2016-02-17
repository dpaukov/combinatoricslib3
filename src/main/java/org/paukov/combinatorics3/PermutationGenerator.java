package org.paukov.combinatorics3;

import java.util.Collection;

public class PermutationGenerator<T> {

    final Collection<T> originalVector;

    public PermutationGenerator(Collection<T> originalVector) {
        this.originalVector = originalVector;
    }

    public SimplePermutationGenerator<T> simple() {
        return new SimplePermutationGenerator<>(originalVector, false);
    }

    public SimplePermutationGenerator<T> simple(boolean treatAsIdentical) {
        return new SimplePermutationGenerator<>(originalVector, treatAsIdentical);
    }
}
