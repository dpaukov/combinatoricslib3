/*
  Combinatorics Library 3
  Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.Iterator;
import java.util.List;

class IntegerPartitionGenerator implements IGenerator<List<Integer>> {

  final Integer value;

  IntegerPartitionGenerator(Integer value) {
    this.value = value;
  }

  @Override
  public Iterator<List<Integer>> iterator() {
    return new IntegerPartitionIterator(this);
  }
}
