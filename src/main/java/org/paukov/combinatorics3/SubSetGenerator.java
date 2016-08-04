package org.paukov.combinatorics3;

import java.util.Collection;
import java.util.List;

public class SubSetGenerator<T> {

    final Collection<T> originalVector;

    public SubSetGenerator(Collection<T> originalVector) {
        this.originalVector = originalVector;
    }

    public IGenerator<List<T>> simple() {
        return new SimpleSubSetGenerator<>(originalVector);
    }
}
