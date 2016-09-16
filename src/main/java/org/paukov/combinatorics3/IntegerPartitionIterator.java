package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dima on 9/16/16.
 */
class IntegerPartitionIterator implements Iterator<List<Integer>> {


    final Integer initialValue;
    List<Integer> _currentPartition = null;

    long _currentIndex = 0;

    /**
     * Helper vectors
     */
    private int[] _mVector = null;
    private int[] _zVector = null;

    /**
     * Stop criteria
     */
    private int _kIndex = 1;

    public IntegerPartitionIterator(IntegerPartitionGenerator generator) {

        initialValue = generator.value;
        _mVector = new int[initialValue + 2];
        _zVector = new int[initialValue+ 2];

        if (initialValue > 0) {
            _currentIndex = 0;
            _kIndex = 1;

            setInternalVectorValue(-1, _zVector, 0);
            setInternalVectorValue(-1, _mVector, 0);

            setInternalVectorValue(0, _zVector, initialValue + 1);
            setInternalVectorValue(0, _mVector, 0);

            setInternalVectorValue(1, _zVector, 1);
            setInternalVectorValue(1, _mVector, initialValue);
        } else {
            _kIndex = 0;
        }
    }


    @Override
    public boolean hasNext() {
        return _kIndex != 0;
    }


    @Override
    public List<Integer> next() {
        _currentIndex++;
        createCurrentPartition(_kIndex);
        int sum = getInternalVectorValue(_kIndex, _mVector)
                * getInternalVectorValue(_kIndex, _zVector);
        if (getInternalVectorValue(_kIndex, _mVector) == 1) {
            _kIndex--;
            sum += getInternalVectorValue(_kIndex, _mVector)
                    * getInternalVectorValue(_kIndex, _zVector);
        }
        if (getInternalVectorValue(_kIndex - 1, _zVector) == getInternalVectorValue(
                _kIndex, _zVector) + 1) {
            _kIndex--;
            setInternalVectorValue(_kIndex, _mVector,
                    getInternalVectorValue(_kIndex, _mVector) + 1);
        } else {
            setInternalVectorValue(_kIndex, _zVector,
                    getInternalVectorValue(_kIndex, _zVector) + 1);
            setInternalVectorValue(_kIndex, _mVector, 1);
        }
        if (sum > getInternalVectorValue(_kIndex, _zVector)) {
            setInternalVectorValue(_kIndex + 1, _zVector, 1);
            setInternalVectorValue(_kIndex + 1, _mVector, sum
                    - getInternalVectorValue(_kIndex, _zVector));
            _kIndex++;
        }

        // return the current partition
        return _currentPartition;
    }



    private void createCurrentPartition(int k) {
        _currentPartition = new ArrayList<>();
        for (int index = 1; index <= k; index++) {
            for (int j = 0; j < getInternalVectorValue(index, _mVector); j++) {
                _currentPartition.add(getInternalVectorValue(index, _zVector));
            }
        }
    }

    private final int getInternalVectorValue(int index, int[] vector) {
        return vector[index + 1];
    }

    private final void setInternalVectorValue(int index, int[] vector, int value) {
        vector[index + 1] = value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "PartitionIterator=[#" + _currentIndex + ", "
                + _currentPartition + "]";
    }
}
