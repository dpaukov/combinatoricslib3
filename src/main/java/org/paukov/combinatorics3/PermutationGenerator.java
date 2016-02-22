/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.Collection;

public class PermutationGenerator<T> {

    public enum TreatDuplicatesAs {
        DIFFERENT,
        IDENTICAL
    }

    final Collection<T> originalVector;

    public PermutationGenerator(Collection<T> originalVector) {
        this.originalVector = originalVector;
    }

    public SimplePermutationGenerator<T> simple() {
        return new SimplePermutationGenerator<>(originalVector, false);
    }

    public SimplePermutationGenerator<T> simple(TreatDuplicatesAs treatAsIdentical) {
        return new SimplePermutationGenerator<>(originalVector, TreatDuplicatesAs.IDENTICAL.equals(treatAsIdentical));
    }

    public PermutationWithRepetitionGenerator<T> withRepetitions(int permutationLength) {
        return new PermutationWithRepetitionGenerator<>(originalVector, permutationLength);
    }

}
