/*
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationGenerator<T> {

  final Collection<T> originalVector;

  PermutationGenerator(Collection<T> originalVector) {
    this.originalVector = originalVector;
  }

  public static <T> boolean hasDuplicates(Collection<T> collection) {
    if (collection.size() <= 1) {
      return false;
    }
    Set<T> set = new HashSet<>(collection);
    return set.size() < collection.size();
  }

  public IGenerator<List<T>> simple() {
    return new SimplePermutationGenerator<>(originalVector, false);
  }

  public IGenerator<List<T>> simple(TreatDuplicatesAs treatAsIdentical) {
    return new SimplePermutationGenerator<>(originalVector,
        TreatDuplicatesAs.IDENTICAL.equals(treatAsIdentical));
  }

  /**
   * Generate KPermutationGenerator, do not allow duplicate elements in input vector.
   * @param length the value of "k" in KPermutationGenerator
   * @return KPermutationGenerator
   */
  public IGenerator<List<T>> k(int length) {
    if (hasDuplicates(originalVector)) {
       throw new IllegalArgumentException("Duplicated elements found in input elements.");
    }
    return k( length, KTreatDuplicatesAs.NOT_ALLOW_DUPLICATED);
  }


  /**
   * Generate KPermutationGenerator.
   * @param length the value of "k" in KPermutationGenerator
   * @param kTreatDuplicatesAs how to handle duplicated element in input vector and permutation stream.
   * @return KPermutationGenerator
   */
  public IGenerator<List<T>> k(int length, KTreatDuplicatesAs kTreatDuplicatesAs) {
    return new KPermutationGenerator<>(originalVector, length, kTreatDuplicatesAs);
  }


  public IGenerator<List<T>> withRepetitions(int permutationLength) {
    return new PermutationWithRepetitionGenerator<>(originalVector, permutationLength);
  }

  public enum TreatDuplicatesAs {
    DIFFERENT,
    IDENTICAL
  }

  /**
   * In K-Permutation, how to handle duplicated element
   * NOT_ALLOW_DUPLICATED: default action, do not allow duplicated element in input vector
   * ALLOW_DUPLICATED_INPUT: allow duplicated element in input vector, while elements in Permutation stream is distinct.
   * ALLOW_DUPLICATED_OUTPUT: allow duplicated element in input vector, and elements in Permutation stream could be duplicated.
   */
  public enum KTreatDuplicatesAs {
    NOT_ALLOW_DUPLICATED,
    ALLOW_DUPLICATED_INPUT,
    ALLOW_DUPLICATED_OUTPUT
  }

}
