/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.*;

class DuplicatedPermutationIterator<T> implements Iterator<List<T>> {

    final int _length;

    List<T> _currentPermutation;
    long _currentIndex = 0;

    private int _data[] = null;
    private boolean _firstIteration = true;
    private List<T> _initialOrderedPermutation;


    DuplicatedPermutationIterator(SimplePermutationGenerator<T> generator) {

        _length = generator._originalVector.size();
        _data = new int[_length];

        List<T> originalVector = generator._originalVector;
        List<T> initialPermutation = new ArrayList<>();

        // Create a set of the initial data
        Set<T> initialSet = new LinkedHashSet<>(originalVector);

        // Create internal data
        int dataValue = 0;
        int dataIndex = 0;

        _initialOrderedPermutation = new ArrayList<>(initialSet);

        for (T value : _initialOrderedPermutation) {

            dataValue++;

            if (!initialPermutation.contains(value)) {
                // Determine how many duplicates of the value in the original
                // vector
                int count = intcountElements(originalVector, value);

                for (int countIndex = 0; countIndex < count; countIndex++) {
                    _data[dataIndex++] = dataValue;
                    initialPermutation.add(value);
                }
            }
        }

        _currentIndex = 0;
        _currentPermutation = new ArrayList<>();

        for (int i = 0; i < _length; i++) {
            int index = _data[i] - 1;
            _currentPermutation.add(_initialOrderedPermutation
                    .get(index));
        }
    }


    @Override
    public boolean hasNext() {
        return !isFinished() || _firstIteration;
    }


    @Override
    public List<T> next() {

        if (_firstIteration) {
            _firstIteration = false;
            return _currentPermutation;
        }

        int k = _data.length - 2;

        while (_data[k] >= _data[k + 1]) {
            k--;
        }

        int l = _data.length - 1;
        while (_data[k] >= _data[l]) {
            l--;
        }
        swap(_data, k, l);
        int length = _data.length - (k + 1);
        for (int i = 0; i < length / 2; i++) {
            swap(_data, k + 1 + i, _data.length - i - 1);
        }

        _currentIndex++;
        _currentPermutation = new ArrayList<>();

        for (int i = 0; i < _length; i++) {
            int index = _data[i] - 1;
            _currentPermutation.add(_initialOrderedPermutation
                    .get(index));
        }

        return _currentPermutation;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }


    @Override
    public String toString() {
        return "DuplicatedPermutationIterator=[#" + (_currentIndex + 1) + ", "
                + _currentPermutation + "]";
    }

    private static void swap(int[] data, int k, int l) {
        int temp = data[k];
        data[k] = data[l];
        data[l] = temp;
    }

    private boolean isFinished() {
        int k = _data.length - 2;
        while (_data[k] >= _data[k + 1]) {
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
