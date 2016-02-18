/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Iterator for the permutation generator
 *
 * @author Dmytro Paukov
 * @version 3.0
 * @see SimplePermutationGenerator
 * @param <T>
 *            Type of elements in the permutations
 */
class SimplePermutationIterator<T> implements Iterator<List<T>> {

    final SimplePermutationGenerator<T> _generator;
    final List<T> _currentPermutation;
    final int _length;
    long _currentIndex = 0;

    private int[] _pZ = null;
    private int[] _pP = null;
    private int[] _pD = null;
    private int m = 0;
    private int w = 0;
    private int pm = 0;
    private int dm = 0;
    private int zpm = 0;

    /**
     * Constructor
     *
     * @param generator
     *            Permutation generator
     */
    SimplePermutationIterator(SimplePermutationGenerator<T> generator) {
        _generator = generator;
        _length = generator._originalVector.size();
        _currentPermutation = new ArrayList<>(generator._originalVector);
        _pZ = new int[_length + 2];
        _pP = new int[_length + 2];
        _pD = new int[_length + 2];
        init();
    }

    /**
     * Initialize the iteration process
     */
    void init() {
        _currentIndex = 0;

        m = 0;
        w = 0;
        pm = 0;
        dm = 0;
        zpm = 0;

        for (int i = 1; i <= _length; i++) {
            _pP[i] = i;
            _pZ[i] = i;
            _pD[i] = -1;
        }
        _pD[1] = 0;
        _pZ[_length + 1] = m = _length + 1;
        _pZ[0] = _pZ[_length + 1];

    }


    /**
     * Return true if the iteration process is finished
     */
    @Override
    public boolean hasNext() {
        return m != 1;
    }


    /**
     * Moves to the next permutation
     */
    @Override
    public List<T> next() {

        for (int i = 1; i <= _length; i++) {
            int index = _pZ[i] - 1;
            setValue(_currentPermutation,i-1, _generator._originalVector.get(index));
        }
        m = _length;
        while (_pZ[_pP[m] + _pD[m]] > m) {
            _pD[m] = -_pD[m];
            m--;
        }
        pm = _pP[m];
        dm = pm + _pD[m];
        w = _pZ[pm];
        _pZ[pm] = _pZ[dm];
        _pZ[dm] = w;
        zpm = _pZ[pm];
        w = _pP[zpm];
        _pP[zpm] = pm;
        _pP[m] = w;
        _currentIndex++;

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
        return "PermutationIterator=[#" + _currentIndex + ", "
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
