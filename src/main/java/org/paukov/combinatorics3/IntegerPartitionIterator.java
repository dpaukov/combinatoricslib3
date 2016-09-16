package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dpaukov on 9/16/16.
 */
class IntegerPartitionIterator implements Iterator<List<Integer>> {


    final Integer initialValue;
    List<Integer> currentPartition = null;

    long currentIndex = 0;

    /**
     * Helper vectors
     */
    private int[] mVector = null;
    private int[] zVector = null;

    /**
     * Stop criteria
     */
    private int kIndex = 1;

    public IntegerPartitionIterator(IntegerPartitionGenerator generator) {

        initialValue = generator.value;
        mVector = new int[initialValue + 2];
        zVector = new int[initialValue+ 2];

        if (initialValue > 0) {
            currentIndex = 0;
            kIndex = 1;

            setInternalVectorValue(-1, zVector, 0);
            setInternalVectorValue(-1, mVector, 0);

            setInternalVectorValue(0, zVector, initialValue + 1);
            setInternalVectorValue(0, mVector, 0);

            setInternalVectorValue(1, zVector, 1);
            setInternalVectorValue(1, mVector, initialValue);
        } else {
            kIndex = 0;
        }
    }


    @Override
    public boolean hasNext() {
        return kIndex != 0;
    }


    @Override
    public List<Integer> next() {
        currentIndex++;
        currentPartition = createCurrentPartition(kIndex, mVector, zVector);
        int sum = getInternalVectorValue(kIndex, mVector)
                * getInternalVectorValue(kIndex, zVector);
        if (getInternalVectorValue(kIndex, mVector) == 1) {
            kIndex--;
            sum += getInternalVectorValue(kIndex, mVector)
                    * getInternalVectorValue(kIndex, zVector);
        }
        if (getInternalVectorValue(kIndex - 1, zVector) == getInternalVectorValue(
                kIndex, zVector) + 1) {
            kIndex--;
            setInternalVectorValue(kIndex, mVector,
                    getInternalVectorValue(kIndex, mVector) + 1);
        } else {
            setInternalVectorValue(kIndex, zVector,
                    getInternalVectorValue(kIndex, zVector) + 1);
            setInternalVectorValue(kIndex, mVector, 1);
        }
        if (sum > getInternalVectorValue(kIndex, zVector)) {
            setInternalVectorValue(kIndex + 1, zVector, 1);
            setInternalVectorValue(kIndex + 1, mVector, sum
                    - getInternalVectorValue(kIndex, zVector));
            kIndex++;
        }

        // return the current partition
        return currentPartition;
    }

    private static List<Integer> createCurrentPartition(int k, int[] mVector, int[] zVector) {
        List<Integer> list = new ArrayList<>();
        for (int index = 1; index <= k; index++) {
            for (int j = 0; j < getInternalVectorValue(index, mVector); j++) {
                list.add(getInternalVectorValue(index, zVector));
            }
        }
        return list;
    }

    private static int getInternalVectorValue(int index, int[] vector) {
        return vector[index + 1];
    }

    private static void setInternalVectorValue(int index, int[] vector, int value) {
        vector[index + 1] = value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "PartitionIterator=[#" + currentIndex + ", "
                + currentPartition + "]";
    }
}
