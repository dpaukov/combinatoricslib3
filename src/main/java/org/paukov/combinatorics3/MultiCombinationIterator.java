/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Multi-combinations iterator for enumerating combinations with repetitions
 * 
 * @author Dmytro Paukov
 * @see MultiCombinationGenerator
 * @param <T>
 *            Type of the elements in the combinations
 * @version 3.0
 */ class MultiCombinationIterator<T> implements
		Iterator<List<T>> {

	/**
	 * Generator
	 */
    final MultiCombinationGenerator<T> generator;

	/**
	 * Current combination
	 */
    List<T> currentCombination = null;

	/**
	 * Index of the current combination
	 */
    long currentIndex = 0;

	/**
	 * Size of the original vector/set
	 */
    final int lengthN;

	/**
	 * Size of the combinations (number of elements) to generate
	 */
    final int lengthK;

	/**
	 * A helper array
	 */
	private int[] bitVector = null;

	/**
	 * Criteria to stop iteration
	 */
	private boolean end = false;

	/**
	 * Constructor
	 *
	 * @param generator
	 *            Multi-combinations generator
	 */
	public MultiCombinationIterator(MultiCombinationGenerator<T> generator) {
		this.generator = generator;
		lengthN = generator.originalVector.size();
		currentCombination = new ArrayList<>();
		bitVector = new int[generator.combinationLength];
		lengthK = generator.combinationLength - 1;
        for (int i = 0; i < generator.combinationLength; i++) {
            bitVector[i] = 0;
        }
        end = false;
        currentIndex = 0;
	}



	/**
	 * Returns true if all combinations were iterated, otherwise false
	 */
	@Override
	public boolean hasNext() {
		return (end != true);
	}

	/**
	 * Moves to the next combination
	 */
	@Override
	public List<T> next() {
		currentIndex++;

		for (int i = 0; i < generator.combinationLength; i++) {
			int index = bitVector[i];
			if (generator.originalVector.size() > 0) {
                setValue(currentCombination, i, generator.originalVector
                        .get(index));
			}
		}

		if (bitVector.length > 0) {
			bitVector[lengthK]++;

			if (bitVector[lengthK] > lengthN - 1) {
				int index = -1;
				for (int i = 1; i <= bitVector.length; i++) {
					if (lengthK - i >= 0) {
						if (bitVector[lengthK - i] < lengthN - 1) {
							index = lengthK - i;
							break;
						}
					}
				}

				if (index != -1) {
					bitVector[index]++;

					for (int j = 1; j < bitVector.length - index; j++) {
						bitVector[index + j] = bitVector[index];
					}

				} else {
					end = true;
				}

			}
		} else {
			end = true;
		}

		// return the current combination
		return new ArrayList<>(currentCombination);
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
		return "MultiCombinationIterator=[#" + currentIndex + ", "
				+ currentCombination + "]";
	}

    private static <T> void setValue(List<T> list, int index, T value) {
        try {
            list.set(index, value);
        } catch (IndexOutOfBoundsException ex) {
            list.add(index, value);
        }
    }

}
