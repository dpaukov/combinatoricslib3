/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
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
 *            Type of the elements of the combinations
 * @version 3.0
 */
class MultiCombinationIterator<T> implements
		Iterator<List<T>> {

    final MultiCombinationGenerator<T> generator;
    List<T> currentCombination = null;


    long currentIndex = 0;
    final int lengthN;
    final int lengthK;

	private int[] bitVector = null;


	// Criteria to stop iterating
	private boolean end = false;


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


	@Override
	public boolean hasNext() {
		return (end != true);
	}


	@Override
	public List<T> next() {
		currentIndex++;

		for (int i = 0; i < generator.combinationLength; i++) {
			int index = bitVector[i];
			if (generator.originalVector.size() > 0) {
                currentCombination.add(i, generator.originalVector.get(index));
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

		// return a copy of the current combination
		return new ArrayList<>(currentCombination);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}


	@Override
	public String toString() {
		return "MultiCombinationIterator=[#" + currentIndex + ", " + currentCombination + "]";
	}
}
