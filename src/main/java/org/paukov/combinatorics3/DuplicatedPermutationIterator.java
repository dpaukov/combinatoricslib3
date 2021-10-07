/*
  Combinatorics Library 3
  Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import static java.lang.Math.toIntExact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class DuplicatedPermutationIterator<T> implements Iterator<List<T>> {

  private final int length;
  private final int[] data;
  private final List<T> initialOrderedPermutation;
  private List<T> currentPermutation;
  private long currentIndex;
  private boolean isFirstIteration = true;

  DuplicatedPermutationIterator(SimplePermutationGenerator<T> generator) {
    this.length = generator.originalVector.size();
    this.data = new int[length];

    List<T> originalVector = generator.originalVector;

    // Create a set of the initial data
    Set<T> initialSet = new LinkedHashSet<>(originalVector);
    this.initialOrderedPermutation = new ArrayList<>(initialSet);
    List<T> initialPermutation = new ArrayList<>();

    int dataValue = 0;
    int dataIndex = 0;
    for (T value : initialOrderedPermutation) {
      dataValue++;
      if (!initialPermutation.contains(value)) {
        // Determine how many duplicates of the value in the original vector.
        int count = intCountElements(originalVector, value);
        for (int countIndex = 0; countIndex < count; countIndex++) {
          this.data[dataIndex++] = dataValue;
          initialPermutation.add(value);
        }
      }
    }

    this.currentIndex = 0;
    this.currentPermutation = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      this.currentPermutation.add(initialOrderedPermutation.get(data[i] - 1));
    }
  }

  private static void swap(int[] data, int k, int l) {
    int temp = data[k];
    data[k] = data[l];
    data[l] = temp;
  }

  private static <T> int intCountElements(List<T> list, T value) {
    return toIntExact(list.stream().filter(item -> item.equals(value)).count());
  }

  @Override
  public boolean hasNext() {
    return !isFinished() || isFirstIteration;
  }

  @Override
  public List<T> next() {
    if (isFirstIteration) {
      isFirstIteration = false;
      return currentPermutation;
    }

    int k = data.length - 2;
    while (data[k] >= data[k + 1]) {
      k--;
    }

    int l = data.length - 1;
    while (data[k] >= data[l]) {
      l--;
    }
    swap(data, k, l);

    int newLength = data.length - (k + 1);
    for (int i = 0; i < newLength / 2; i++) {
      swap(data, k + 1 + i, data.length - i - 1);
    }

    currentIndex++;
    currentPermutation = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      currentPermutation.add(initialOrderedPermutation.get(data[i] - 1));
    }

    return currentPermutation;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return "DuplicatedPermutationIterator=[#" + (currentIndex + 1) + ", " + currentPermutation
        + "]";
  }

  private boolean isFinished() {
    int index = data.length - 2;
    while (data[index] >= data[index + 1]) {
      index--;
      if (index < 0) {
        return true;
      }
    }
    return false;
  }
}
