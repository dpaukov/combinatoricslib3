/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator for the simple combination generator
 * 
 * @author Dmytro Paukov
 * @version 3.0
 * @see SimpleCombinationGenerator
 * @param <T>
 *            Type of the elements in the combinations
 */
 class SimpleCombinationIterator<T> implements Iterator<List<T>> {

    private final SimpleCombinationGenerator<T> generator;
    private List<T> currentSimpleCombination = null;

	private long currentIndex = 0;
    private final int lengthN;
    private final int lengthK;

	// Internal array
	private int[] bitVector = null;

	//Criteria to stop iterating
	private int endIndex = 0;


	SimpleCombinationIterator(SimpleCombinationGenerator<T> generator) {
		this.generator = generator;
		lengthN = generator.originalVector.size();
		lengthK = generator.combinationLength;
		currentSimpleCombination = new ArrayList<T>();
		bitVector = new int[lengthK + 1];
        for (int i = 0; i <= lengthK; i++) {
            bitVector[i] = i;
        }
        if (lengthN > 0) {
            endIndex = 1;
        }
        currentIndex = 0;
	}


	/**
	 * Returns true if all combinations were iterated, otherwise false
	 */
	@Override
	public boolean hasNext() {
		return !((endIndex == 0) || (lengthK > lengthN));
	}

	/**
	 * Moves to the next combination
	 */
	@Override
	public List<T> next() {
		currentIndex++;

		for (int i = 1; i <= lengthK; i++) {
			int index = bitVector[i] - 1;
			if (generator.originalVector.size() > 0) {
                setValue(currentSimpleCombination, i - 1,
                        generator.originalVector.get(index));
			}
		}

		endIndex = lengthK;

		while (bitVector[endIndex] == lengthN - lengthK + endIndex) {
			endIndex--;
			if (endIndex == 0)
				break;
		}
		bitVector[endIndex]++;
		for (int i = endIndex + 1; i <= lengthK; i++) {
			bitVector[i] = bitVector[i - 1] + 1;
		}

		// return the current combination
		return new ArrayList<>(currentSimpleCombination);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}


	@Override
	public String toString() {
		return "SimpleCombinationIterator=[#" + currentIndex + ", " + currentSimpleCombination + "]";
	}

    private static <T> void setValue(List<T> list, int index, T value) {
        if (index < list.size()) {
            list.set(index, value);
        } else {
            list.add(index, value);
        }
    }
}
