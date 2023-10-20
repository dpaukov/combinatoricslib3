/*
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator over the all subsets.
 *
 * @param <T> Type of the elements in the subsets.
 * @author Dmytro Paukov
 * @version 3.1.0
 * @see SubSetGenerator
 */
class SimpleSubSetIterator<T> implements Iterator<List<T>> {

  private final SimpleSubSetGenerator<T> generator;
  private final int length;

  private final List<T> currentSubSet;
  private long currentIndex;

  /** Internal bit vector, representing the subset. */
  private final BitSet bitVector;

  SimpleSubSetIterator(final SimpleSubSetGenerator<T> generator) {
    this.generator = generator;
    this.length = generator.originalVector.size();
    this.currentSubSet = new ArrayList<>(length);
    this.bitVector = new BitSet(length + 2);
    this.currentIndex = 0;
  }

  /**
   * Returns true if iteration is done, otherwise false.
   *
   * @see Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return !bitVector.get(length + 1);
  }

  /**
   * Returns the next subset if it is available.
   *
   * @see Iterator#next()
   */
  @Override
  public List<T> next() {
    this.currentIndex++;
    List<T> originalVector = this.generator.originalVector;
    BitSet bitVector = this.bitVector;
    int subSetSize = currentSubSet.size();
    int j = 0;

    for (int i = bitVector.nextSetBit(1); i >= 0; i = bitVector.nextSetBit(i + 1)) {
      T e = originalVector.get(i - 1);
      if (j < subSetSize) {
        currentSubSet.set(j++, e);
      } else {
        currentSubSet.add(e);
      }
    }

    // Do we have leftovers?
    if (j < subSetSize) {
      currentSubSet.subList(j, subSetSize).clear();
    }

    int i = 1;
    while (bitVector.get(i)) {
      bitVector.clear(i);
      i++;
    }
    bitVector.set(i);

    return new ArrayList<>(currentSubSet);
  }


  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }


  @Override
  public String toString() {
    return "SimpleSubSetIterator=[#" + currentIndex + ", " + currentSubSet + "]";
  }
}
