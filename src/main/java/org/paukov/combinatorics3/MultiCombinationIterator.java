/*
  Combinatorics Library 3
  Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Multi-combinations iterator for enumerating combinations with repetitions
 *
 * @param <T> Type of the elements of the combinations
 * @author Dmytro Paukov
 * @version 3.0
 * @see MultiCombinationGenerator
 */
class MultiCombinationIterator<T> implements Iterator<List<T>> {

  private final MultiCombinationGenerator<T> generator;
  private final List<T> currentCombination = new ArrayList<>();
  private final int[] bitVector;
  private long currentIndex;
  private boolean isEnd; // Criteria to stop iterating


  MultiCombinationIterator(MultiCombinationGenerator<T> generator) {
    this.generator = generator;
    this.bitVector = new int[generator.combinationLength];
    for (int i = 0; i < generator.combinationLength; i++) {
      bitVector[i] = 0;
    }
    this.isEnd = false;
    this.currentIndex = 0;
  }

  private void setValue(int index, T value) {
    if (index < this.currentCombination.size()) {
      this.currentCombination.set(index, value);
    } else {
      this.currentCombination.add(index, value);
    }
  }

  @Override
  public boolean hasNext() {
    return !isEnd;
  }

  @Override
  public List<T> next() {
    this.currentIndex++;

    int size = this.generator.originalVector.size();
    for (int i = 0; i < this.generator.combinationLength; i++) {
      int index = bitVector[i];
      if (size > 0) {
        this.setValue(i, this.generator.originalVector.get(index));
      }
    }

    int combinationLength = this.generator.combinationLength-1;
    if (this.bitVector.length > 0) {
      bitVector[combinationLength]++;
      if (bitVector[combinationLength] > size - 1) {
        int index = -1;
        for (int i = 1; i <= bitVector.length; i++) {
          if (combinationLength - i >= 0) {
            if (bitVector[combinationLength - i] < size - 1) {
              index = combinationLength - i;
              break;
            }
          }
        }
        if (index != -1) {
          this.bitVector[index]++;
          for (int j = 1; j < this.bitVector.length - index; j++) {
            this.bitVector[index + j] = this.bitVector[index];
          }
        } else {
          this.isEnd = true;
        }
      }
    } else {
      this.isEnd = true;
    }

    // return a copy of the current combination
    return new ArrayList<>(this.currentCombination);
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return "MultiCombinationIterator=[#" + this.currentIndex + ", " + this.currentCombination + "]";
  }
}
