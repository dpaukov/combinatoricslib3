/*
  Combinatorics Library 3
  Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.Collection;
import java.util.List;

/**
 * A generator for iterating over the combinations.
 * @param <T> Type of the elements in the combinations.
 */
public class CombinationGenerator<T> {

  final Collection<T> originalVector;

  CombinationGenerator(Collection<T> originalVector) {
    this.originalVector = originalVector;
  }

  /**
   * Creates a generator to produce combinations of the given length of elements.
   */
  public IGenerator<List<T>> simple(int length) {
    return new SimpleCombinationGenerator<>(originalVector, length);
  }

  /**
   * Creates a generator to produce combinations with repetitions of the given length of elements.
   */
  public IGenerator<List<T>> multi(int length) {
    return new MultiCombinationGenerator<>(originalVector, length);
  }
}
