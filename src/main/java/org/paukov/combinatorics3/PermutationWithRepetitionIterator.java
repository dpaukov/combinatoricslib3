/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PermutationWithRepetitionIterator <T> implements Iterator<List<T>> {

    final PermutationWithRepetitionGenerator<T> generator;

    List<T> currentPermutation = null;
    long currentIndex = 0;

    final int originalVectorSize;
    final int permutationLength;

    // Internal data
    private int[] bitVector = null;

    public PermutationWithRepetitionIterator(
            PermutationWithRepetitionGenerator<T> generator) {
        this.generator = generator;
        originalVectorSize = generator.originalVector.size();
        permutationLength = generator.permutationLength;

        List<T> list = new ArrayList<>(permutationLength);
        T defaultValue = generator.originalVector.get(0);
        for (int i = 0; i < permutationLength; i++) {
            list.add(defaultValue);
        }

        currentPermutation = new ArrayList<>(list);

        bitVector = new int[permutationLength + 2];
        currentIndex = 0;
    }


    @Override
    public boolean hasNext() {
        return (bitVector[permutationLength] != 1);
    }


    @Override
    public List<T> next() {
        currentIndex++;

        for (int j = permutationLength - 1; j >= 0; j--) {
            currentPermutation.set(j, generator.originalVector.get(bitVector[j]));
        }

        int i = 0;
        while (bitVector[i] == originalVectorSize - 1) {
            if (i < permutationLength + 1)
                bitVector[i] = 0;
            else {
                bitVector[permutationLength] = 1;
                return new ArrayList<>(currentPermutation);
            }
            i++;
        }

        bitVector[i]++;
        return new ArrayList<>(currentPermutation);

    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }


    @Override
    public String toString() {
        return "PermutationWithRepetitionIterator=[#" + currentIndex + ", " + currentPermutation + "]";
    }
}
