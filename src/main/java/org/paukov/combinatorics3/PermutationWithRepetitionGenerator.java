package org.paukov.combinatorics3;


import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class PermutationWithRepetitionGenerator<T> implements IGenerator<List<T>> {


    final List<T> _originalVector;
    final int _permutationLength;


    PermutationWithRepetitionGenerator(
            Collection<T> originalVector, int permutationLength) {
        _originalVector = new ArrayList<>(originalVector);
        _permutationLength = permutationLength;
    }


    @Override
    public Iterator<List<T>> iterator() {
        return new PermutationWithRepetitionIterator<T>(this);
    }


    @Override
    public Stream<List<T>> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }

}
