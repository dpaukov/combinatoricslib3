/*
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.paukov.combinatorics3.PermutationGenerator.KTreatDuplicatesAs;
import static org.paukov.combinatorics3.PermutationGenerator.KTreatDuplicatesAs.*;

/**
 * This generator generates k-permutations of the specified initial vector
 * <p>
 * A k-permutation is an ordering of a subset in the context of all possible
 * orderings. For example, the set containing 2 digits of 123, has
 * six permutations: 12, 21, 13, 31, 23 and 32.
 * <p>
 * This is an example of the k-permutations of 3 string items (apple, orange,
 * cherry):
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
 * @version 3.4
 */
class KPermutationGenerator<T> implements IGenerator<List<T>> {

  final List<T> originalVector;
  final int length;
  final KTreatDuplicatesAs kTreatDuplicatesAs;

  /**
   * Constructor
   *
   * @param vector Vector which is used for k-permutation generation
   * @param length number of elements in k-permutation
   */
  KPermutationGenerator(Collection<T> vector,
                        int length,
                        KTreatDuplicatesAs kTreatDuplicatesAs) {
    this.originalVector = new ArrayList<>(vector);
    this.length = length;
    this.kTreatDuplicatesAs = kTreatDuplicatesAs;
  }

  @Override
  public Iterator<List<T>> iterator() {
    switch (kTreatDuplicatesAs) {
      case NOT_ALLOW_DUPLICATED:
        return Generator.combination(originalVector)
                .simple(length)
                .stream()
                .flatMap(combination -> Generator.permutation(combination).simple().stream())
                .iterator();
      case ALLOW_DUPLICATED_INPUT:
        return Generator.combination(originalVector)
                .simple(length)
                .stream()
                .distinct()
                .flatMap(combination -> Generator.permutation(combination).simple().stream())
                .iterator();
      case ALLOW_DUPLICATED_OUTPUT:
        return Generator.combination(originalVector)
                .simple(length)
                .stream()
                .flatMap(combination -> Generator.permutation(combination).simple(PermutationGenerator.TreatDuplicatesAs.IDENTICAL).stream())
                .iterator();
      default:
        throw new UnsupportedOperationException();
    }
  }

  @Override
  public Stream<List<T>> stream() {
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
  }
}

