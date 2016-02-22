package org.paukov.combinatorics3;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PermutationWithRepetitionIterator <T> implements Iterator<List<T>> {

    final PermutationWithRepetitionGenerator<T> _generator;

    List<T> _currentPermutation = null;
    long _currentIndex = 0;

    final int _n;
    final int _k;

    /**
     * Internal data
     */
    private int[] _bitVector = null;

    public PermutationWithRepetitionIterator(
            PermutationWithRepetitionGenerator<T> generator) {
        _generator = generator;
        _n = generator._originalVector.size();
        _k = generator._permutationLength;

        List<T> list = new ArrayList<>(_k);
        T defaultValue = generator._originalVector.get(0);
        for (int i = 0; i < _k; i++) {
            list.add(defaultValue);
        }

        _currentPermutation = new ArrayList<>(list);

        _bitVector = new int[_k + 2];
        init();
    }


    private void init() {
        for (int j = 0; j <= _k; j++) {
            _bitVector[j] = 0;
        }
        _currentIndex = 0;
    }

    /**
     * Returns true if all permutations have been iterated
     *
     * @see Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return (_bitVector[_k] != 1);
    }

    /**
     * Moves to the next permutation
     *
     * @see Iterator#next()
     */
    @Override
    public List<T> next() {
        _currentIndex++;

        for (int j = _k - 1; j >= 0; j--) {
            int index = _bitVector[j];
            setValue(_currentPermutation, j, _generator._originalVector.get(index));
        }

        int i = 0;
        while (_bitVector[i] == _n - 1) {
            if (i < _k + 1)
                _bitVector[i] = 0;
            else {
                _bitVector[_k] = 1;
                return new ArrayList<>(_currentPermutation);
            }
            i++;
        }

        _bitVector[i]++;
        return new ArrayList<>(_currentPermutation);

    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the current permutation as a string
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PermutationWithRepetitionIterator=[#" + _currentIndex + ", "
                + _currentPermutation + "]";
    }

    private static <T> void setValue(List<T> list, int index, T value) {
        try {
            list.set(index, value);
        } catch (IndexOutOfBoundsException ex) {
            list.add(index, value);
        }
    }
}
