/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
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

  private List<T> currentPermutation;
  private long currentIndex;

  private int data[];
  private boolean firstIteration = true;
  private List<T> initialOrderedPermutation;


  DuplicatedPermutationIterator(SimplePermutationGenerator<T> generator) {

    length = generator.originalVector.size();
    data = new int[length];

    List<T> originalVector = generator.originalVector;
    List<T> initialPermutation = new ArrayList<>();

    // Create a set of the initial data
    Set<T> initialSet = new LinkedHashSet<>(originalVector);

    // Create internal data
    int dataValue = 0;
    int dataIndex = 0;

    initialOrderedPermutation = new ArrayList<>(initialSet);

    for (T value : initialOrderedPermutation) {

      dataValue++;

      if (!initialPermutation.contains(value)) {
        // Determine how many duplicates of the value in the original
        // vector
        int count = intcountElements(originalVector, value);

        for (int countIndex = 0; countIndex < count; countIndex++) {
          data[dataIndex++] = dataValue;
          initialPermutation.add(value);
        }
      }
    }

    currentIndex = 0;
    currentPermutation = new ArrayList<>();

    for (int i = 0; i < length; i++) {
      int index = data[i] - 1;
      currentPermutation.add(initialOrderedPermutation
          .get(index));
    }
  }

  private static void swap(int[] data, int k, int l) {
    int temp = data[k];
    data[k] = data[l];
    data[l] = temp;
  }

  private static <T> int intcountElements(List<T> list, T value) {
    return toIntExact(list.stream().filter(item -> item.equals(value)).count());
  }

  @Override
  public boolean hasNext() {
    return !isFinished() || firstIteration;
  }

  @Override
  public List<T> next() {

    if (firstIteration) {
      firstIteration = false;
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
      int index = data[i] - 1;
      currentPermutation.add(initialOrderedPermutation
          .get(index));
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
    int k = data.length - 2;
    while (data[k] >= data[k + 1]) {
      k--;
      if (k < 0) {
        return true;
      }
    }
    return false;
  }
}
