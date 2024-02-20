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

  /**
   * Creates a combination generator for a given set of arguments.
   * @param args A list of arguments (elements) for the generator.
   * @param <T> A type of the arguments.
   * @return An instance of the CombinationGenerator with the provided elements.
   */
  @SafeVarargs
  public static <T> CombinationGenerator<T> combination(T... args) {
    return new CombinationGenerator<>(asList(args));
  }

  /**
   * Creates a combination generator for a given set of arguments.
   * @param collection A collection of the elements for the generator.
   * @param <T> A type of the elements.
   * @return An instance of the CombinationGenerator with the provided elements.
   */
  public static <T> CombinationGenerator<T> combination(Collection<T> collection) {
    return new CombinationGenerator<>(collection);
  }

  /**
   * Creates a permutation generator for a given set of arguments.
   * @param args A list of arguments (elements) for the generator.
   * @param <T> A type of the arguments.
   * @return An instance of the PermutationGenerator with the provided elements.
   */
  @SafeVarargs
  public static <T> PermutationGenerator<T> permutation(T... args) {
    return new PermutationGenerator<>(asList(args));
  }

  /**
   * Creates a permutation generator for a given collection of the elements.
   * @param collection A collection of the elements for the generator.
   * @param <T> A type of the elements.
   * @return An instance of the PermutationGenerator with the provided elements.
   */
  public static <T> PermutationGenerator<T> permutation(Collection<T> collection) {
    return new PermutationGenerator<>(collection);
  }

  /**
   * Creates a subset generator for a given collection of the elements.
   * @param collection A collection of the elements for the generator.
   * @param <T> A type of the elements.
   * @return An instance of the generator that produces subsets of the elements.
   */
  public static <T> SubSetGenerator<T> subset(Collection<T> collection) {
    return new SubSetGenerator<>(collection);
  }

  /**
   * Creates a subset generator for a given list of the arguments.
   * @param args A list of the arguments for the generator.
   * @param <T> A type of the elements.
   * @return An instance of the generator that produces subsets of the elements.
   */
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
