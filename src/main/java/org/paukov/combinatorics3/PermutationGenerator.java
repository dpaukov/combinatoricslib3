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

  public IGenerator<List<T>> withRepetitions(int permutationLength) {
    return new PermutationWithRepetitionGenerator<>(originalVector, permutationLength);
  }

  public enum TreatDuplicatesAs {
    DIFFERENT,
    IDENTICAL
  }

}
