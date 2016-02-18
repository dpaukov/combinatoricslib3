/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
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
 class SimpleCombinationIterator<T> implements
		Iterator<List<T>> {

	/**
	 * Generator
	 */
	final SimpleCombinationGenerator<T> generator;

	/**
	 * Current simple combination
	 */
	List<T> currentSimpleCombination = null;

	/**
	 * Index of the current combination
	 */
	long currentIndex = 0;

	/**
	 * Size of the original vector/set
	 */
	final int lengthN;

	/**
	 * Size of the generated combination.
	 */
	final int lengthK;

	/**
	 * Helper array
	 */
	int[] bitVector = null;

	/**
	 * Criteria to stop iteration
	 */
	private int endIndex = 0;

	/**
	 * Constructor
	 *
	 * @param generator
	 *            Generator of the simple combinations
	 */
	SimpleCombinationIterator(SimpleCombinationGenerator<T> generator) {
		this.generator = generator;
		lengthN = generator.originalVector.size();
		lengthK = generator.combinationLength;
		currentSimpleCombination = new ArrayList<T>();
		bitVector = new int[lengthK + 1];
		init();
	}

	/**
	 * Initialization
	 */
	private void init() {

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
		return new ArrayList<T>(currentSimpleCombination);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the current combination as a string
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleCombinationIterator=[#" + currentIndex + ", "
				+ currentSimpleCombination + "]";
	}

    private static <T> void setValue(List<T> list, int index, T value) {
        try {
            list.set(index, value);
        } catch (IndexOutOfBoundsException ex) {
            list.add(index, value);
        }
    }

}
