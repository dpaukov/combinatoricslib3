/*
  Combinatorics Library 3
  Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator for the cartesian product generator.
 *
 * @param <T> Type of the elements in the cartesian product.
 * @author Julius Iglesia
 * @version 3.0
 * @see CartesianProductGenerator
 */
class CartesianProductIterator<T> implements Iterator<List<T>> {

  private final CartesianProductGenerator<T> generator;
  private final int[] indices;

  private List<T> current;
  private int nextIndex;
  private int index = 0;
  private boolean hasEmptyList = false;

  CartesianProductIterator(CartesianProductGenerator<T> generator) {
    this.generator = generator;

    // Start from the last index.
    int vectorSize = this.generator.originalVector.size();
    this.nextIndex = vectorSize - 1;

    // For tracking the indices of the product.
    this.indices = new int[vectorSize];

    // For tracking the lengths of the lists.
    for (int i = 0; i < vectorSize; i++) {
      this.hasEmptyList = this.hasEmptyList || this.generator.originalVector.get(i).size() == 0;
    }
  }

  /**
   * Returns true if all cartesian products were iterated, otherwise false.
   */
  @Override
  public boolean hasNext() {
    return !this.hasEmptyList && this.nextIndex >= 0;
  }

  /**
   * Moves to the next Cartesian product.
   */
  @Override
  public List<T> next() {
    if (this.index == 0) {
      return generateCartesianProduct();
    }

    if (this.nextIndex < 0) {
      throw new RuntimeException("No more cartesian product.");
    }

    // Move to the next element.
    this.indices[this.nextIndex]++;

    for (int i = this.nextIndex + 1; i < this.generator.originalVector.size(); i++) {
      this.indices[i] = 0;
    }

    return generateCartesianProduct();
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return "CartesianProductIterator=[#" + this.index + ", " + this.current + "]";
  }

  private List<T> generateCartesianProduct() {
    this.current = new ArrayList<>();
    for (int i = 0; i < this.generator.originalVector.size(); i++) {
      this.current.add(this.generator.originalVector.get(i).get(indices[i]));
    }

    // After generating the current, check if has still next cartesian product,
    // this will be used by #hasNext function
    checkIfHasNextCartesianProduct();
    this.index++;

    return this.current;
  }

  private void checkIfHasNextCartesianProduct() {
    // Check if it still has a cartesian product by finding an array that has more elements left.
    this.nextIndex = this.generator.originalVector.size() - 1;
    while (this.nextIndex >= 0 &&
        this.indices[this.nextIndex] + 1 >= this.generator.originalVector.get(this.nextIndex)
            .size()) {
      this.nextIndex--;
    }
  }
}
