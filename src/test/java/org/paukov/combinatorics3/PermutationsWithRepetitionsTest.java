/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class PermutationsWithRepetitionsTest {

  @Test
  public void test_simple_permutation_with_repetition() {
    List<List<Integer>> permutations = Generator.permutation(1, 2, 3)
        .withRepetitions(2)
        .stream()
        .collect(toList());

    assertThat(permutations).hasSize(9);

    System.out.println("Simple permutations with repetitions of (1, 2, 3):");
    permutations.stream().forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly(1, 1);
    assertThat(permutations.get(1)).containsExactly(2, 1);
    assertThat(permutations.get(2)).containsExactly(3, 1);
    assertThat(permutations.get(3)).containsExactly(1, 2);
    assertThat(permutations.get(4)).containsExactly(2, 2);
    assertThat(permutations.get(5)).containsExactly(3, 2);
    assertThat(permutations.get(6)).containsExactly(1, 3);
    assertThat(permutations.get(7)).containsExactly(2, 3);
    assertThat(permutations.get(8)).containsExactly(3, 3);

  }

  @Test
  public void test_one_permutation_of_three_with_repetition() {
    List<List<Integer>> permutations = Generator.permutation(asList(1, 2, 3))
        .withRepetitions(1)
        .stream()
        .collect(toList());

    assertThat(permutations).hasSize(3);

    System.out.println("One permutations with repetitions of (1, 2, 3):");
    permutations.stream().forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly(1);
    assertThat(permutations.get(1)).containsExactly(2);
    assertThat(permutations.get(2)).containsExactly(3);

  }


  @Test
  public void test_zero_permutation_of_three_with_repetition() {
    List<List<Integer>> permutations = Generator.permutation(new Integer[]{1, 2, 3})
        .withRepetitions(0)
        .stream()
        .collect(toList());

    assertThat(permutations).hasSize(1);

    System.out.println("Zero permutations with repetitions of (1, 2, 3):");
    permutations.stream().forEach(System.out::println);

    assertThat(permutations.get(0)).isEmpty();

  }

  @Test
  public void test_one_permutation_of_one_with_repetition() {
    List<List<String>> permutations = Generator.permutation("a")
        .withRepetitions(1)
        .stream()
        .collect(toList());

    assertThat(permutations).hasSize(1);

    System.out.println("One permutations with repetitions of ('a'):");
    permutations.stream().forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a");
  }


  @Test
  public void test_two_permutation_of_one_with_repetition() {
    List<List<String>> permutations = Generator.permutation("a")
        .withRepetitions(2)
        .stream()
        .collect(toList());

    assertThat(permutations).hasSize(1);

    System.out.println("Two permutations with repetitions of ('a'):");
    permutations.stream().forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a", "a");
  }

  @Test
  public void test_zero_permutation_of_one_with_repetition() {
    List<List<String>> permutations = Generator.permutation("a")
        .withRepetitions(0)
        .stream()
        .collect(toList());

    assertThat(permutations).hasSize(1);

    System.out.println("Zero permutations with repetitions of ('a'):");
    permutations.stream().forEach(System.out::println);

    assertThat(permutations.get(0)).isEmpty();
  }


  @Test
  public void test_filter_permutation_of_with_repetition() {
    List<List<String>> permutations = Generator.permutation("a", "b", "c", "d")
        .withRepetitions(3)
        .stream()
        .filter(strings -> !PermutationGenerator.hasDuplicates(strings))
        .collect(toList());

    assertThat(permutations).hasSize(24);

    System.out
        .println("Three permutations with repetitions of ('a', 'b', 'c', 'd') without duplicates:");
    permutations.stream().forEach(System.out::println);
  }


  @Test
  public void test_three_tuples_of_two_elements() {
    List<List<Integer>> permutations = Generator.permutation(0, 1)
        .withRepetitions(3)
        .stream()
        .collect(toList());

    assertThat(permutations).hasSize(8);

    System.out.println("Three tuples of (0, 1):");
    permutations.stream().forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly(0, 0, 0);
    assertThat(permutations.get(1)).containsExactly(1, 0, 0);
    assertThat(permutations.get(2)).containsExactly(0, 1, 0);
    assertThat(permutations.get(3)).containsExactly(1, 1, 0);
    assertThat(permutations.get(4)).containsExactly(0, 0, 1);
    assertThat(permutations.get(5)).containsExactly(1, 0, 1);
    assertThat(permutations.get(6)).containsExactly(0, 1, 1);
    assertThat(permutations.get(7)).containsExactly(1, 1, 1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void test_permutation_iterator_remove_operation() {
    Iterator<List<Integer>> permutations = Generator.permutation(asList(1, 2, 3))
        .withRepetitions(2)
        .iterator();

    assertThat(permutations).isNotNull();
    assertThat(permutations.hasNext()).isTrue();

    // this method should throw a UnsupportedOperationException
    permutations.remove();
  }

  @Test
  public void test_permutation_with_repetition_iterator_toString() {
    Iterator<List<Integer>> permutations = Generator.permutation(asList(1, 2, 3))
        .withRepetitions(2)
        .iterator();

    assertThat(permutations).isNotNull();
    assertThat(permutations.hasNext()).isTrue();
    assertThat(permutations.next()).containsExactly(1, 1);
    assertThat(permutations.toString()).isEqualTo("PermutationWithRepetitionIterator=[#1, [1, 1]]");
  }

}
