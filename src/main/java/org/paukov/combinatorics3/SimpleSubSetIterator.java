/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator over the all subsets
 *
 * @author Dmytro Paukov
 * @version 3.0
 * @see SubSetGenerator
 *
 * @param <T>
 *            Type of the elements of the subsets
 */
class SimpleSubSetIterator<T> implements Iterator<List<T>> {

    final SimpleSubSetGenerator<T> generator;
    final int _length;

    List<T> _currentSubSet = null;
    long _currentIndex = 0;

    /**
     * internal bit vector, representing the subset
     */
    private int[] _bitVector = null;


    public SimpleSubSetIterator(SimpleSubSetGenerator<T> generator) {
        this.generator = generator;
        _length = generator.originalVector.size();
        _currentSubSet = new ArrayList<>();
        _bitVector = new int[_length + 2];
        _currentIndex = 0;
    }

    /**
     * Returns true if iteration is done, otherwise false
     *
     * @see Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return _bitVector[_length + 1] != 1;
    }

    /**
     * Returns the next subset if it is available
     *
     * @see Iterator#next()
     */
    @Override
    public List<T> next() {
        _currentIndex++;
        _currentSubSet.clear();
        for (int index = 1; index <= _length; index++) {
            if (_bitVector[index] == 1) {
                T value = this.generator.originalVector.get(index - 1);
                _currentSubSet.add(value);
            }
        }
        int i = 1;
        while (_bitVector[i] == 1) {
            _bitVector[i] = 0;
            i++;
        }
        _bitVector[i] = 1;

        return new ArrayList<>(_currentSubSet);
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }


    @Override
    public String toString() {
        return "SubSetIterator=[#" + _currentIndex + ", " + _currentSubSet + "]";
    }


}
