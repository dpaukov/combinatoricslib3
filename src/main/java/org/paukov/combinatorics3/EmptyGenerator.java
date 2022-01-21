package org.paukov.combinatorics3;

import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Always empty stub generator.
 */
class EmptyGenerator<T> implements IGenerator<T> {
  private static final EmptyGenerator<?> EMPTY = new EmptyGenerator<>();

  private EmptyGenerator() {
  }

  static <T> EmptyGenerator<T> emptyGenerator() {
    @SuppressWarnings("unchecked")
    EmptyGenerator<T> g = (EmptyGenerator<T>) EMPTY;
    return g;
  }

  @Override
  public Stream<T> stream() {
    return Stream.empty();
  }

  @Override
  public Iterator<T> iterator() {
    return Collections.emptyIterator();
  }
}
