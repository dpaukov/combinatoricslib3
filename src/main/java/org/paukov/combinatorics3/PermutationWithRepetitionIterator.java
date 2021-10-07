/*
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PermutationWithRepetitionIterator<T> implements Iterator<List<T>> {

  final PermutationWithRepetitionGenerator<T> generator;
  private final List<T> currentPermutation;
  private final int[] bitVector;
  private long currentIndex;

  PermutationWithRepetitionIterator(
      PermutationWithRepetitionGenerator<T> generator) {
    this.generator = generator;

    List<T> list = new ArrayList<>(this.generator.permutationLength);
    T defaultValue = generator.originalVector.get(0);
    for (int i = 0; i < this.generator.permutationLength; i++) {
      list.add(defaultValue);
    }

    this.currentPermutation = new ArrayList<>(list);

    this.bitVector = new int[this.generator.permutationLength + 2];
    this.currentIndex = 0;
  }


  @Override
  public boolean hasNext() {
    return bitVector[this.generator.permutationLength] != 1;
  }


  @Override
  public List<T> next() {
    this.currentIndex++;

    for (int j = this.generator.permutationLength - 1; j >= 0; j--) {
      this.currentPermutation.set(j, this.generator.originalVector.get(this.bitVector[j]));
    }

    int i = 0;
    while (this.bitVector[i] == this.generator.originalVector.size() - 1) {
      if (i < this.generator.permutationLength + 1) {
        this.bitVector[i] = 0;
      } else {
        this.bitVector[this.generator.permutationLength] = 1;
        return new ArrayList<>(this.currentPermutation);
      }
      i++;
    }

    this.bitVector[i]++;
    return new ArrayList<>(this.currentPermutation);
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return "PermutationWithRepetitionIterator=[#" + currentIndex + ", " + currentPermutation + "]";
  }
}
