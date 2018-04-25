/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class IntegerPartitionIterator implements Iterator<List<Integer>> {

    private class InternalVector {

        final int[] internalVector;

        InternalVector(int size, int value0, int value1, int value2) {
            internalVector = new int[size + 2];
            internalVector[0] = value0;
            internalVector[1] = value1;
            internalVector[2] = value2;
        }

        int get(int index){
            return internalVector[index + 1];
        }

        void set(int index, int value) {
            internalVector[index + 1] = value;
        }
    }

    private final InternalVector mVector;
    private final InternalVector zVector;

    private List<Integer> currentPartition = null;
    private long currentIndex = 0;
    private int stopIndex = 1; //it should be 0 to stop the iteration


    IntegerPartitionIterator(IntegerPartitionGenerator generator) {

        int partitionValue = generator.value;

        if (partitionValue > 0) {
            currentIndex = 0;
            stopIndex = 1;
            mVector = new InternalVector(partitionValue, 0, 0, partitionValue);
            zVector = new InternalVector(partitionValue, 0, partitionValue + 1, 1);
        } else {
            stopIndex = 0;
            mVector = new InternalVector(1, 0, 0, 0);
            zVector = new InternalVector(1, 0, 0, 0);
        }
    }


    @Override
    public boolean hasNext() {
        return stopIndex != 0;
    }


    @Override
    public List<Integer> next() {
        currentIndex++;
        currentPartition = createCurrentPartition(stopIndex, mVector, zVector);

        int sum = mVector.get(stopIndex) * zVector.get(stopIndex);

        if (mVector.get(stopIndex) == 1) {
            stopIndex--;
            sum += mVector.get(stopIndex) * zVector.get(stopIndex);
        }

        if (zVector.get(stopIndex - 1) == zVector.get(stopIndex) + 1) {
            stopIndex--;
            mVector.set(stopIndex, mVector.get(stopIndex) + 1);
        } else {
            zVector.set(stopIndex, zVector.get(stopIndex) + 1);
            mVector.set(stopIndex, 1);
        }
        if (sum > zVector.get(stopIndex)) {
            zVector.set(stopIndex + 1, 1);
            mVector.set(stopIndex + 1, sum - zVector.get(stopIndex) );
            stopIndex++;
        }

        return currentPartition;
    }

    private static List<Integer> createCurrentPartition(int k, InternalVector mVector, InternalVector zVector) {
        List<Integer> list = new ArrayList<>();
        for (int index = 1; index <= k; index++) {
            for (int j = 0; j < mVector.get(index); j++) {
                list.add(zVector.get(index));
            }
        }
        return list;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "IntegerPartitionIterator=[#" + currentIndex + ", " + currentPartition + "]";
    }
}
