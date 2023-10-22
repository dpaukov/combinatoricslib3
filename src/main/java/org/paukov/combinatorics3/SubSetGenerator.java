/*
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.Collection;
import java.util.List;

/**
 * A generator for iterating over the subsets.
 * @param <T> Type of the elements in the sebsets.
 */
public class SubSetGenerator<T> {

  final Collection<T> originalVector;

  SubSetGenerator(Collection<T> originalVector) {
    this.originalVector = originalVector;
  }

  public IGenerator<List<T>> simple() {
    return new SimpleSubSetGenerator<>(originalVector);
  }
}
