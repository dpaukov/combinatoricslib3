/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator for the cartesian product generator
 *
 * @param <T> Type of the elements in the cartesian product
 * @author Julius Iglesia
 * @version 3.0
 * @see CartesianProductGenerator
 */
class CartesianProductIterator<T> implements Iterator<List<T>> {

    private List<List<T>> vector;

    private final int vectorSize;

    private int nextIndex;

    private final int[] indices;

    private List<T> current;

    private int index = 0;

    private boolean hasEmptyList = false;

    CartesianProductIterator(CartesianProductGenerator<T> generator) {
        vector = generator.originalVector;
        vectorSize = generator.originalVector.size();

        // start from the last index
        nextIndex = vectorSize - 1;

        // for tracking the indices of the product
        indices = new int[this.vectorSize];

        // for the tracking the lengths of the lists
        for (int i = 0; i < vectorSize; i++) {
            hasEmptyList = hasEmptyList || vector.get(i).size() == 0;
        }
    }

    /**
     * Returns true if all cartesian products were iterated, otherwise false
     */
    @Override
    public boolean hasNext() {
        return !hasEmptyList && nextIndex >= 0;
    }

    /**
     * Moves to the next Cartesian product
     */
    @Override
    public List<T> next() {
        if (index == 0) {
            return generateCartesianProduct();
        }

        if (nextIndex < 0) {
            throw new RuntimeException("No more cartesian product.");
        }

        // Move to the next element
        indices[nextIndex]++;

        for (int i = nextIndex + 1; i < vectorSize; i++) {
            indices[i] = 0;
        }

        return generateCartesianProduct();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "CartesianProductIterator=[#" + index + ", " + current + "]";
    }

    private List<T> generateCartesianProduct() {
        current = new ArrayList<>();
        for (int i = 0; i < vectorSize; i++) {
            current.add(vector.get(i).get(indices[i]));
        }

        // After generating the current, check if has still next cartesian product,
        // this will be used by #hasNext function
        checkIfHasNextCartesianProduct();
        index++;

        return current;
    }

    private void checkIfHasNextCartesianProduct() {
        // Check if has still cartesian product by finding an array that has more elements left
        nextIndex = vectorSize - 1;
        while (nextIndex >= 0 &&
                indices[nextIndex] + 1 >= vector.get(nextIndex).size()) {
            nextIndex--;
        }
    }
}
