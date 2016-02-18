/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.Collection;


public class CombinationGenerator<T> {

    final Collection<T> originalVector;

    public CombinationGenerator(Collection<T> originalVector) {
        this.originalVector = originalVector;
    }

    public SimpleCombinationGenerator<T> simple(int length) {
        return new SimpleCombinationGenerator<>(originalVector, length);
    }

    public MultiCombinationGenerator<T> multi(int length) {
        return new MultiCombinationGenerator<>(originalVector, length);
    }
}
