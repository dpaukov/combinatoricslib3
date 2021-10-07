/*
 * Combinatorics Library 3
 * Copyright 2009-2021 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This generator generates k-permutations of the specified initial vector
 * <p>
 * A k-permutation is an ordering of a subset in the context of all possible orderings. For example,
 * the set containing 2 digits of 123, has six permutations: 12, 21, 13, 31, 23 and 32.
 * <p>
 * This is an example of the k-permutations of 3 string items (apple, orange, cherry):
 * <p>
 * <blockquote>
 *
 * <pre>
 *
 *     List<List<String>> permutations =
 *             Generator.permutation(Arrays.asList("apple", "orange", "cherry"))
 *                     .k(2)
 *                     .stream()
 *                     .collect(toList());
 *     permutations.stream().forEach(System.out::println);
 *
 * </pre>
 *
 * </blockquote>
 * <p>
 * And the result
 * <p>
 * <blockquote>
 *
 * <pre>
 *   [apple, orange]
 *   [orange, apple]
 *   [apple, cherry]
 *   [cherry, apple]
 *   [orange, cherry]
 *   [cherry, orange]
 * </pre>
 *
 * </blockquote>
 * <p>
 *
 * @param <T> Type of the elements in the permutations
 * @author Charlie Feng
 * @version 3.3.2
 */
class KPermutationGenerator<T> implements IGenerator<List<T>> {

  final Collection<T> originalVector;
  final int length;
  final boolean hasDuplicates;

  /**
   * Constructor
   *
   * @param vector Vector which is used for k-permutation generation
   * @param length number of elements in k-permutation
   */
  KPermutationGenerator(Collection<T> vector,
      int length,
      boolean hasDuplicates) {
    this.originalVector = vector;
    this.length = length;
    this.hasDuplicates = hasDuplicates;
  }

  @Override
  public Iterator<List<T>> iterator() {
    if (hasDuplicates) {
      return Generator.combination(originalVector)
          .simple(length)
          .stream()
          .distinct()
          .flatMap(combination -> Generator.permutation(combination).simple().stream())
          .iterator();
    }

    return Generator.combination(originalVector)
        .simple(length)
        .stream()
        .flatMap(combination -> Generator.permutation(combination)
            .simple(PermutationGenerator.TreatDuplicatesAs.IDENTICAL).stream())
        .iterator();
  }

  @Override
  public Stream<List<T>> stream() {
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
  }
}

