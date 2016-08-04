/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.*;

class DuplicatedPermutationIterator<T> implements Iterator<List<T>> {

    final int length;

    List<T> currentPermutation;
    long currentIndex = 0;

    private int data[] = null;
    private boolean firstIteration = true;
    private List<T> initialOrderedPermutation;


    DuplicatedPermutationIterator(SimplePermutationGenerator<T> generator) {

        length = generator.originalVector.size();
        data = new int[length];

        List<T> originalVector = generator.originalVector;
        List<T> initialPermutation = new ArrayList<>();

        // Create a set of the initial data
        Set<T> initialSet = new LinkedHashSet<>(originalVector);

        // Create internal data
        int dataValue = 0;
        int dataIndex = 0;

        initialOrderedPermutation = new ArrayList<>(initialSet);

        for (T value : initialOrderedPermutation) {

            dataValue++;

            if (!initialPermutation.contains(value)) {
                // Determine how many duplicates of the value in the original
                // vector
                int count = intcountElements(originalVector, value);

                for (int countIndex = 0; countIndex < count; countIndex++) {
                    data[dataIndex++] = dataValue;
                    initialPermutation.add(value);
                }
            }
        }

        currentIndex = 0;
        currentPermutation = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            int index = data[i] - 1;
            currentPermutation.add(initialOrderedPermutation
                    .get(index));
        }
    }


    @Override
    public boolean hasNext() {
        return !isFinished() || firstIteration;
    }


    @Override
    public List<T> next() {

        if (firstIteration) {
            firstIteration = false;
            return currentPermutation;
        }

        int k = data.length - 2;

        while (data[k] >= data[k + 1]) {
            k--;
        }

        int l = data.length - 1;
        while (data[k] >= data[l]) {
            l--;
        }
        swap(data, k, l);
        int newlength = data.length - (k + 1);
        for (int i = 0; i < newlength / 2; i++) {
            swap(data, k + 1 + i, data.length - i - 1);
        }

        currentIndex++;
        currentPermutation = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            int index = data[i] - 1;
            currentPermutation.add(initialOrderedPermutation
                    .get(index));
        }

        return currentPermutation;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }


    @Override
    public String toString() {
        return "DuplicatedPermutationIterator=[#" + (currentIndex + 1) + ", " + currentPermutation + "]";
    }

    private static void swap(int[] data, int k, int l) {
        int temp = data[k];
        data[k] = data[l];
        data[l] = temp;
    }

    private boolean isFinished() {
        int k = data.length - 2;
        while (data[k] >= data[k + 1]) {
            k--;
            if (k < 0) {
                return true;
            }
        }
        return false;
    }

    private static <T> int intcountElements(List<T> list, T value) {
        int count = 0;
        for (T element : list) {
            if (element.equals(value))
                count++;
        }
        return count;
    }
}
