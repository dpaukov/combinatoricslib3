/*
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator for the simple combination generator.
 *
 * @param <T> Type of the elements in the combinations.
 * @author Dmytro Paukov
 * @version 3.0
 * @see SimpleCombinationGenerator
 */
class SimpleCombinationIterator<T> implements Iterator<List<T>> {

  private final SimpleCombinationGenerator<T> generator;
  private final List<T> currentCombination = new ArrayList<>();
  // Internal array
  private final int[] bitVector;
  private long currentIndex;
  //Criteria to stop iterating the combinations.
  private int endIndex = 0;

  SimpleCombinationIterator(SimpleCombinationGenerator<T> generator) {
    this.generator = generator;
    this.bitVector = new int[generator.combinationLength + 1];
    for (int i = 0; i <= generator.combinationLength; i++) {
      this.bitVector[i] = i;
    }
    if (generator.originalVector.size() > 0) {
      this.endIndex = 1;
    }
    this.currentIndex = 0;
  }

  private void setValue(int index, T value) {
    if (index < this.currentCombination.size()) {
      this.currentCombination.set(index, value);
    } else {
      this.currentCombination.add(index, value);
    }
  }

  /**
   * Returns true if all combinations were iterated, otherwise false
   */
  @Override
  public boolean hasNext() {
    return !((this.endIndex == 0) || (this.generator.combinationLength
        > this.generator.originalVector.size()));
  }

  /**
   * Moves to the next combination
   */
  @Override
  public List<T> next() {
    this.currentIndex++;

    for (int i = 1; i <= this.generator.combinationLength; i++) {
      int index = this.bitVector[i] - 1;
      if (this.generator.originalVector.size() > 0) {
        this.setValue(i - 1, this.generator.originalVector.get(index));
      }
    }

    this.endIndex = this.generator.combinationLength;
    while (this.bitVector[this.endIndex]
        == this.generator.originalVector.size() - this.generator.combinationLength + endIndex) {
      this.endIndex--;
      if (endIndex == 0) {
        break;
      }
    }
    this.bitVector[this.endIndex]++;
    for (int i = this.endIndex + 1; i <= this.generator.combinationLength; i++) {
      this.bitVector[i] = this.bitVector[i - 1] + 1;
    }

    // Return a copy of the current combination.
    return new ArrayList<>(this.currentCombination);
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return "SimpleCombinationIterator=[#" + this.currentIndex + ", " + this.currentCombination
        + "]";
  }
}
