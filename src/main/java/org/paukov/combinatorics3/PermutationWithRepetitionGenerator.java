/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class PermutationWithRepetitionGenerator<T> implements IGenerator<List<T>> {

    final List<T> originalVector;
    final int permutationLength;

    PermutationWithRepetitionGenerator(
            Collection<T> originalVector, int permutationLength) {
        this.originalVector = new ArrayList<>(originalVector);
        this.permutationLength = permutationLength;
    }


    @Override
    public Iterator<List<T>> iterator() {
        return new PermutationWithRepetitionIterator<>(this);
    }


    @Override
    public Stream<List<T>> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }

}
