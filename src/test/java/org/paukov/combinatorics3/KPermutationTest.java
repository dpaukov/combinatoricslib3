/**
 * Combinatorics Library 3
 * Copyright 2009-2021 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.paukov.combinatorics3.PermutationGenerator.TreatDuplicatesAs;

public final class KPermutationTest {

  @Test
  public void test_k_2_of_3_permutation() {
    List<List<Integer>> permutations = Generator.permutation(1, 2, 3)
        .k(2)
        .stream()
        .collect(toList());

    assertThat(permutations).hasSize(6);

    System.out.println("k2-permutations of (1, 2, 3):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly(1, 2);
    assertThat(permutations.get(1)).containsExactly(2, 1);
    assertThat(permutations.get(2)).containsExactly(1, 3);
    assertThat(permutations.get(3)).containsExactly(3, 1);
    assertThat(permutations.get(4)).containsExactly(2, 3);
    assertThat(permutations.get(5)).containsExactly(3, 2);
  }

  @Test
  public void test_k_1_of_1_permutation() {
    List<List<Integer>> permutations =
        Generator.permutation(1)
            .k(1)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(1);

    System.out.println("k1-permutations of (1):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsOnly(1);
  }

  @Test
  public void test_k_0_of_0_permutation() {
    List<List<Integer>> permutations =
        Generator.permutation(new Integer[]{})
            .k(0)
            .stream()
            .collect(toList());

    assertThat(permutations).isEmpty();
  }

  @Test
  public void test_k_2_of_3_with_duplicates_permutation() {
    List<List<String>> permutations =
        Generator.permutation("a", "a", "b")
            .k(2, TreatDuplicatesAs.IDENTICAL)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(3);

    System.out.println("k2-permutations of (a, a, b):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a", "a");
    assertThat(permutations.get(1)).containsExactly("a", "b");
    assertThat(permutations.get(2)).containsExactly("b", "a");
  }

  @Test
  public void test_k_2_of_3_permutation_ignore_duplicates() {
    List<List<String>> permutations =
        Generator.permutation("a", "a", "b")
            .k(2)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(6);

    System.out.println("k2-permutations of (a, a, b) ignore duplicates:");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a", "a");
    assertThat(permutations.get(1)).containsExactly("a", "a");
    assertThat(permutations.get(2)).containsExactly("a", "b");
    assertThat(permutations.get(3)).containsExactly("b", "a");
    assertThat(permutations.get(4)).containsExactly("a", "b");
    assertThat(permutations.get(5)).containsExactly("b", "a");
  }

  @Test
  public void test_k_1_of_3_with_duplicates_permutation() {
    List<List<String>> permutations =
        Generator.permutation("a", "a", "b")
            .k(1, TreatDuplicatesAs.IDENTICAL)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(2);

    System.out.println("k1-permutations of (a, a, b):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a");
    assertThat(permutations.get(1)).containsExactly("b");
  }

  @Test
  public void test_k_1_of_3_permutation_ignore_duplicates() {
    List<List<String>> permutations =
        Generator.permutation("a", "a", "b")
            .k(1)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(3);

    System.out.println("k1-permutations of (a, a, b) ignore duplicates:");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a");
    assertThat(permutations.get(1)).containsExactly("a");
    assertThat(permutations.get(2)).containsExactly("b");
  }

  @Test
  public void test_all_identical_k_2_of_3_permutation() {
    List<List<String>> permutations =
        Generator.permutation("a", "a", "a")
            .k(2, TreatDuplicatesAs.IDENTICAL)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(1);

    System.out.println("k2-permutations of (a, a, a):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a", "a");
  }

  @Test
  public void test_all_identical_k_2_of_3_permutation_ignore_duplicates() {
    List<List<String>> permutations =
        Generator.permutation("a", "a", "a")
            .k(2)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(6);

    System.out.println("k2-permutations of (a, a, a) ignore duplicates:");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a", "a");
    assertThat(permutations.get(1)).containsExactly("a", "a");
    assertThat(permutations.get(2)).containsExactly("a", "a");
    assertThat(permutations.get(3)).containsExactly("a", "a");
    assertThat(permutations.get(4)).containsExactly("a", "a");
    assertThat(permutations.get(5)).containsExactly("a", "a");
  }


  @Test
  public void test_k_3_of_3_abc_permutation() {
    List<List<String>> permutations =
        Generator.permutation(asList("a", "b", "c"))
            .k(3)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(6);

    System.out.println("k3-permutations of (a, b, c):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a", "b", "c");
    assertThat(permutations.get(1)).containsExactly("a", "c", "b");
    assertThat(permutations.get(2)).containsExactly("c", "a", "b");
    assertThat(permutations.get(3)).containsExactly("c", "b", "a");
    assertThat(permutations.get(4)).containsExactly("b", "c", "a");
    assertThat(permutations.get(5)).containsExactly("b", "a", "c");
  }

  @Test
  public void test_k_2_of_3_abc_permutation() {
    List<List<String>> permutations =
        Generator.permutation(asList("a", "b", "c"))
            .k(2)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(6);

    System.out.println("k2-permutations of (a, b, c):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a", "b");
    assertThat(permutations.get(1)).containsExactly("b", "a");
    assertThat(permutations.get(2)).containsExactly("a", "c");
    assertThat(permutations.get(3)).containsExactly("c", "a");
    assertThat(permutations.get(4)).containsExactly("b", "c");
    assertThat(permutations.get(5)).containsExactly("c", "b");
  }

  @Test
  public void test_k_1_of_3_abc_permutation() {
    List<List<String>> permutations =
        Generator.permutation(asList("a", "b", "c"))
            .k(1)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(3);

    System.out.println("k3-permutations of (a, b, c):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("a");
    assertThat(permutations.get(1)).containsExactly("b");
    assertThat(permutations.get(2)).containsExactly("c");
  }


  @Test
  public void test_k_0_of_3_abc_permutation() {
    List<List<String>> permutations =
        Generator.permutation(asList("a", "b", "c"))
            .k(0)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(0);
  }


  @Test
  public void test_k_4_of_3_abc_permutation() {
    List<List<String>> permutations =
        Generator.permutation(asList("a", "b", "c"))
            .k(4)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(0);
  }


  @Test
  public void test_any_expression_k2_permutation() {
    List<List<String>> permutations =
        Generator.permutation(new String[]{"x", "x^2", "x+1"})
            .k(2)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(6);

    System.out.println("k2-permutations of (x, x^2, x+1):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly("x", "x^2");
    assertThat(permutations.get(1)).containsExactly("x^2", "x");
    assertThat(permutations.get(2)).containsExactly("x", "x+1");
    assertThat(permutations.get(3)).containsExactly("x+1", "x");
    assertThat(permutations.get(4)).containsExactly("x^2", "x+1");
    assertThat(permutations.get(5)).containsExactly("x+1", "x^2");
  }

  @Test
  public void test_k_3_of_4_permutation_with_duplicates() {
    List<List<Integer>> permutations =
        Generator.permutation(asList(1, 2, 2, 3))
            .k(3, TreatDuplicatesAs.IDENTICAL)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(12);

    System.out.println("k3-permutations of (1, 2, 2, 3):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly(1, 2, 2);
    assertThat(permutations.get(1)).containsExactly(2, 1, 2);
    assertThat(permutations.get(2)).containsExactly(2, 2, 1);
    assertThat(permutations.get(3)).containsExactly(1, 2, 3);
    assertThat(permutations.get(4)).containsExactly(1, 3, 2);
    assertThat(permutations.get(5)).containsExactly(3, 1, 2);
    assertThat(permutations.get(6)).containsExactly(3, 2, 1);
    assertThat(permutations.get(7)).containsExactly(2, 3, 1);
    assertThat(permutations.get(8)).containsExactly(2, 1, 3);
    assertThat(permutations.get(9)).containsExactly(2, 2, 3);
    assertThat(permutations.get(10)).containsExactly(2, 3, 2);
    assertThat(permutations.get(11)).containsExactly(3, 2, 2);
  }

  @Test
  public void test_k_3_of_4_permutation_ignore_duplicates() {
    List<List<Integer>> permutations =
        Generator.permutation(asList(1, 2, 2, 3))
            .k(3)
            .stream()
            .collect(toList());

    assertThat(permutations).hasSize(24);

    System.out.println("k3-permutations of (1, 2, 2, 3):");
    permutations.forEach(System.out::println);

    assertThat(permutations.get(0)).containsExactly(1, 2, 2);
    assertThat(permutations.get(1)).containsExactly(1, 2, 2);
    assertThat(permutations.get(2)).containsExactly(2, 1, 2);
    assertThat(permutations.get(3)).containsExactly(2, 2, 1);
    assertThat(permutations.get(4)).containsExactly(2, 2, 1);
    assertThat(permutations.get(5)).containsExactly(2, 1, 2);
    assertThat(permutations.get(6)).containsExactly(1, 2, 3);
    assertThat(permutations.get(7)).containsExactly(1, 3, 2);
    assertThat(permutations.get(8)).containsExactly(3, 1, 2);
    assertThat(permutations.get(9)).containsExactly(3, 2, 1);
    assertThat(permutations.get(10)).containsExactly(2, 3, 1);
    assertThat(permutations.get(11)).containsExactly(2, 1, 3);
    assertThat(permutations.get(12)).containsExactly(1, 2, 3);
    assertThat(permutations.get(13)).containsExactly(1, 3, 2);
    assertThat(permutations.get(14)).containsExactly(3, 1, 2);
    assertThat(permutations.get(15)).containsExactly(3, 2, 1);
    assertThat(permutations.get(16)).containsExactly(2, 3, 1);
    assertThat(permutations.get(17)).containsExactly(2, 1, 3);
    assertThat(permutations.get(18)).containsExactly(2, 2, 3);
    assertThat(permutations.get(19)).containsExactly(2, 3, 2);
    assertThat(permutations.get(20)).containsExactly(3, 2, 2);
    assertThat(permutations.get(21)).containsExactly(3, 2, 2);
    assertThat(permutations.get(22)).containsExactly(2, 3, 2);
    assertThat(permutations.get(23)).containsExactly(2, 2, 3);
  }

  @Test
  public void test_k_permutation_iterator_remove_operation() {
    Iterator<List<Integer>> permutations =
        Generator.permutation(asList(1, 2, 3))
            .k(2)
            .iterator();

    assertThat(permutations).isNotNull();
    assertThat(permutations.hasNext()).isTrue();
    assertThrows(UnsupportedOperationException.class, permutations::remove);
  }
}
