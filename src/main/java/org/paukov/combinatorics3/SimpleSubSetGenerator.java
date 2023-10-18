/*
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


class SimpleSubSetGenerator<T> implements IGenerator<List<T>> {

  final List<T> originalVector;

  SimpleSubSetGenerator(Collection<T> originalVector) {
    this.originalVector = new ArrayList<>(originalVector);
  }

  @Override
  public Iterator<List<T>> iterator() {
    return new SimpleSubSetIterator<>(this);
  }
}
