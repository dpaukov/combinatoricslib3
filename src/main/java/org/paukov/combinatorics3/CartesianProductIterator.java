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
 * @author Julius Iglesia
 * @version 3.0
 * @see CartesianProductGenerator
 * @param <T>
 *            Type of the elements in the cartesian product
 */
 class CartesianProductIterator<T> implements Iterator<List<T>> {
    
    private final CartesianProductGenerator<T> generator;
    
    private List<List<T>> vector;
    
    private final int vectorSize;
    
    private int nextIndex;
    
    private final int[] indices;

    private List<T> current;
    
    private int index = 0;
    
    private boolean hasEmptyList = false;

    CartesianProductIterator(CartesianProductGenerator<T> generator) {
        this.generator = generator;
        this.vector = this.generator.originalVector;
        this.vectorSize = this.generator.originalVector.size();
        
        // start from the last index
        this.nextIndex = this.vectorSize - 1;
        
        // for tracking the indices of the product
        this.indices = new int[this.vectorSize];
        
        // for the tracking the lengths of the lists
        for (int i = 0; i < this.vectorSize; i++) {
            this.hasEmptyList = this.hasEmptyList || this.vector.get(i).size() == 0;
        }
    }

    /**
     * Returns true if all cartesian products were iterated, otherwise false
     */
    @Override
    public boolean hasNext() {
        return !this.hasEmptyList && this.nextIndex >= 0;
    }

    /**
     * Moves to the next Cartesian product
     */
    @Override
    public List<T> next() {
        if (this.index == 0) {
            return this.generateCartesianProduct();
        }
        
        if(this.nextIndex < 0) {
            throw new RuntimeException("No more cartesian product.");
        }
        
        // Move to the next element
        this.indices[this.nextIndex]++;
        
        for (int i = this.nextIndex + 1; i < this.vectorSize; i++) {
            this.indices[i] = 0;
        }
        
        return this.generateCartesianProduct();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
        return "CartesianProductIterator=[#" + this.index + ", " + this.current + "]";
    }
    
    private List<T> generateCartesianProduct() {
        final List<T> list = new ArrayList<>();
        for (int i = 0; i < this.vectorSize; i++){
            list.add(this.vector.get(i).get(this.indices[i]));
        }
        
        // After generating the current, check if has still next cartesian product,
        // this will be used by #hasNext function
        this.checkIfHasNextCartesianProduct();
        
        this.current = new ArrayList<>(list);
        this.index++;
        
        return list;
    }
    
    private void checkIfHasNextCartesianProduct() {
        // Check if has still cartesian product by finding an array that has more elements left
        this.nextIndex = this.vectorSize - 1;
        while (this.nextIndex >= 0 && 
               this.indices[this.nextIndex] + 1 >= this.vector.get(this.nextIndex).size()) {
            this.nextIndex--;
        }
    }
}
