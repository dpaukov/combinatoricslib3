package org.paukov.combinatorics3;


import java.util.List;

public class CombinationGenerator<T> {

    final List<T> originalVector;

    public CombinationGenerator(List<T> originalVector) {
        this.originalVector = originalVector;
    }

    public SimpleCombinationGenerator<T> simple(int length) {
        return new SimpleCombinationGenerator<>(originalVector, length);
    }

    public MultiCombinationGenerator<T> multi(int length) {
        return new MultiCombinationGenerator<>(originalVector, length);
    }
}
