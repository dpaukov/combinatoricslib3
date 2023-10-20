/*
  Combinatorics Library 3
  Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.List;

/**
 * The main class that contains the methods to create specific supported generators.
 */
public class Generator {

  @SafeVarargs
  public static <T> CombinationGenerator<T> combination(T... args) {
    return new CombinationGenerator<>(asList(args));
  }

  public static <T> CombinationGenerator<T> combination(Collection<T> collection) {
    return new CombinationGenerator<>(collection);
  }

  @SafeVarargs
  public static <T> PermutationGenerator<T> permutation(T... args) {
    return new PermutationGenerator<>(asList(args));
  }

  public static <T> PermutationGenerator<T> permutation(Collection<T> collection) {
    return new PermutationGenerator<>(collection);
  }

  public static <T> SubSetGenerator<T> subset(Collection<T> collection) {
    return new SubSetGenerator<>(collection);
  }

  @SafeVarargs
  public static <T> SubSetGenerator<T> subset(T... args) {
    return new SubSetGenerator<>(asList(args));
  }

  public static IGenerator<List<Integer>> partition(Integer value) {
    return new IntegerPartitionGenerator(value);
  }

  @SafeVarargs
  public static <T> IGenerator<List<T>> cartesianProduct(List<T>... args) {
    return cartesianProduct(asList(args));
  }

  public static <T> IGenerator<List<T>> cartesianProduct(Collection<List<T>> collection) {
    return new CartesianProductGenerator<>(collection);
  }
}
