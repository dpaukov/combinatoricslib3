/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator for the combination of lists generator
 * 
 * @author Julius Iglesia
 * @version 3.0
 * @see SimpleCombinationFromListGenerator
 * @param <T>
 *            Type of the elements in the combinations
 */
 class SimpleCombinationFromListIterator<T> implements Iterator<List<T>> {
    
    private final SimpleCombinationFromListGenerator<T> generator;
    
    private List<List<T>> vector;
    
    private final int vectorSize;
    
    private int nextIndex;
    
    private final int[] indices;
    
    private final int[] listSizes;
    
    private List<T> current;
    
    private int index = 0;
    
    public SimpleCombinationFromListIterator(SimpleCombinationFromListGenerator<T> generator) {
        this.generator = generator;
        this.vector = this.generator.originalVector;
        this.vectorSize = this.generator.originalVector.size();
        
        // start from the last index
        this.nextIndex = this.vectorSize - 1;
        
        // for tracking the indices of the combination
        this.indices = new int[this.vectorSize];
        
        // for the tracking the lengths of the lists
        this.listSizes = new int[this.vectorSize];
        for (int i = 0; i < this.vectorSize; i++) {
            this.listSizes[i] = this.vector.size();
        }
    }

    /**
     * Returns true if all combinations were iterated, otherwise false
     */
    @Override
    public boolean hasNext() {
        return this.nextIndex >= 0;
    }

    /**
     * Moves to the next combination
     */
    @Override
    public List<T> next() {
        if (this.index == 0) {
            return this.generateCombination();
        }
        
        if(this.nextIndex < 0) {
            throw new RuntimeException("No more combination.");
        }
        
        // Move to the next element
        this.indices[this.nextIndex]++;
        
        for (int i = this.nextIndex + 1; i < this.vectorSize; i++) {
            this.indices[i] = 0;
        }
        
        return this.generateCombination();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
        return "SimpleCombinationOfListsIterator=[#" + this.index + ", " + this.current + "]";
    }
    
    private List<T> generateCombination() {
        final List<T> list = new ArrayList<>();
        for (int i = 0; i < this.vectorSize; i++){
            list.add(this.vector.get(i).get(this.indices[i]));
        }
        
        // After generating the current, check if has still next combination,
        // this will be used by #hasNext function
        this.checkIfHasNextCombination();
        
        this.current = new ArrayList<>(list);
        this.index++;
        
        return list;
    }
    
    private void checkIfHasNextCombination() {
        // Check if has still combination by finding an array that has more elements left
        this.nextIndex = this.vectorSize - 1;
        while (this.nextIndex >= 0 && 
               this.indices[this.nextIndex] + 1 >= this.vector.get(this.nextIndex).size()) {
            this.nextIndex--;
        }
    }
}
