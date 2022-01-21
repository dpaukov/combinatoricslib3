/*
  Combinatorics Library 3
  Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This interface is implemented by all generic generators in the library
 *
 * @param <T> The type of the elements
 * @author Dmytro Paukov
 * @version 3.0
 */
public interface IGenerator<T> extends Iterable<T> {

  default Stream<T> stream() {
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
  }
}
