/*
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator for the permutation generator
 *
 * @param <T> Type of elements in the permutations
 * @author Dmytro Paukov
 * @version 3.0
 * @see SimplePermutationGenerator
 */
class SimplePermutationIterator<T> implements Iterator<List<T>> {

  private final SimplePermutationGenerator<T> generator;
  private final List<T> currentPermutation;
  private final int length;
  private long currentIndex;

  private final int[] array1;
  private final int[] array2;
  private final int[] array3;

  private int positionIndex;

  SimplePermutationIterator(SimplePermutationGenerator<T> generator) {
    this.generator = generator;
    this.length = generator.originalVector.size();
    this.currentPermutation = new ArrayList<>(generator.originalVector);
    this.array1 = new int[length + 2];
    this.array2 = new int[length + 2];
    this.array3 = new int[length + 2];
    this.currentIndex = 0;

    for (int i = 1; i <= length; i++) {
      this.array2[i] = i;
      this.array1[i] = i;
      this.array3[i] = -1;
    }
    this.array3[1] = 0;
    this.array1[length + 1] = this.positionIndex = this.length + 1;
    this.array1[0] = this.array1[this.length + 1];
  }

  @Override
  public boolean hasNext() {
    return positionIndex != 1;
  }


  @Override
  public List<T> next() {
    for (int i = 1; i <= length; i++) {
      int index = array1[i] - 1;
      currentPermutation.set(i - 1, generator.originalVector.get(index));
    }
    positionIndex = length;
    while (array1[array2[positionIndex] + array3[positionIndex]] > positionIndex) {
      array3[positionIndex] = -array3[positionIndex];
      positionIndex--;
    }
    int array2value = array2[positionIndex];
    int array3value = array2value + array3[positionIndex];
    int array1value = array1[array2value];
    array1[array2value] = array1[array3value];
    array1[array3value] = array1value;
    int tmp = array1[array2value];
    array1value = array2[tmp];
    array2[tmp] = array2value;
    array2[positionIndex] = array1value;
    currentIndex++;

    return new ArrayList<>(this.currentPermutation);
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }


  @Override
  public String toString() {
    return "SimplePermutationIterator=[#" + this.currentIndex + ", " + this.currentPermutation + "]";
  }
}
