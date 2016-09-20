/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;


public class SimplePermutationTest {

    @Test
    public void test_simple_permutation() {

        List<List<Integer>> permutations =
                Generator.permutation(1, 2, 3)
                        .simple()
                        .stream()
                        .collect(Collectors.<List<Integer>>toList());

        assertThat(permutations).hasSize(6);

        System.out.println("Simple permutations of (1, 2, 3):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsExactly(1, 2, 3);
        assertThat(permutations.get(1)).containsExactly(1, 3, 2);
        assertThat(permutations.get(2)).containsExactly(3, 1, 2);
        assertThat(permutations.get(3)).containsExactly(3, 2, 1);
        assertThat(permutations.get(4)).containsExactly(2, 3, 1);
        assertThat(permutations.get(5)).containsExactly(2, 1, 3);

    }

    @Test
    public void test_simple_one_permutation() {

        List<List<Integer>> permutations =
                Generator.permutation(1)
                        .simple()
                        .stream()
                        .collect(Collectors.<List<Integer>>toList());

        assertThat(permutations).hasSize(1);

        System.out.println("Simple one permutations of (1):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsOnly(1);
    }

    @Test
    public void test_simple_empty_permutation() {

        List<List<Integer>> permutations =
                Generator.permutation(new Integer[]{})
                        .simple()
                        .stream()
                        .collect(Collectors.<List<Integer>>toList());

        assertThat(permutations).isEmpty();
    }

    @Test
    public void test_identical_permutation() {

        List<List<String>> permutations =
                Generator.permutation("a", "a", "b")
                        .simple()
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(permutations).hasSize(3);

        System.out.println("Identical permutations of (a, a, b):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsExactly("a", "a", "b");
        assertThat(permutations.get(1)).containsExactly("a", "b", "a");
        assertThat(permutations.get(2)).containsExactly("b", "a", "a");

    }


    @Test
    public void test_all_identical_permutation() {

        List<List<String>> permutations =
                Generator.permutation("a", "a", "a")
                        .simple()
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(permutations).hasSize(1);

        System.out.println("Identical permutations of (a, a, a):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsExactly("a", "a", "a");
    }

    @Test
    public void test_identical_permutation_treat_as_different() {

        List<List<String>> permutations =
                Generator.permutation("a", "a", "b")
                        .simple(PermutationGenerator.TreatDuplicatesAs.DIFFERENT)
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(permutations).hasSize(3);

        System.out.println("Identical permutations of (a, a, b):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsExactly("a", "a", "b");
        assertThat(permutations.get(1)).containsExactly("a", "b", "a");
        assertThat(permutations.get(2)).containsExactly("b", "a", "a");
    }

    @Test
    public void test_identical_permutation_treat_as_identical() {

        List<List<String>> permutations =
                Generator.permutation("a", "a", "b")
                        .simple(PermutationGenerator.TreatDuplicatesAs.IDENTICAL)
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(permutations).hasSize(6);

        System.out.println("Identical permutations of (a, a, b):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsExactly("a", "a", "b");
        assertThat(permutations.get(1)).containsExactly("a", "b", "a");
        assertThat(permutations.get(2)).containsExactly("b", "a", "a");
        assertThat(permutations.get(3)).containsExactly("b", "a", "a");
        assertThat(permutations.get(4)).containsExactly("a", "b", "a");
        assertThat(permutations.get(5)).containsExactly("a", "a", "b");
    }


    @Test
    public void test_all_identical_permutation_treat_as_identical() {

        List<List<String>> permutations =
                Generator.permutation("x", "x", "x")
                        .simple(PermutationGenerator.TreatDuplicatesAs.IDENTICAL)
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(permutations).hasSize(6);

        System.out.println("Identical permutations of (x, x, x):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsExactly("x", "x", "x");
        assertThat(permutations.get(1)).containsExactly("x", "x", "x");
        assertThat(permutations.get(2)).containsExactly("x", "x", "x");
        assertThat(permutations.get(3)).containsExactly("x", "x", "x");
        assertThat(permutations.get(4)).containsExactly("x", "x", "x");
        assertThat(permutations.get(5)).containsExactly("x", "x", "x");

    }

    @Test
    public void test_abc_permutation() {

        List<List<String>> permutations =
                Generator.permutation(Arrays.asList("a", "b", "c"))
                        .simple()
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(permutations).hasSize(6);

        System.out.println("Identical permutations of (a, b, c):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsExactly("a", "b", "c");
        assertThat(permutations.get(1)).containsExactly("a", "c", "b");
        assertThat(permutations.get(2)).containsExactly("c", "a", "b");
        assertThat(permutations.get(3)).containsExactly("c", "b", "a");
        assertThat(permutations.get(4)).containsExactly("b", "c", "a");
        assertThat(permutations.get(5)).containsExactly("b", "a", "c");

    }

    @Test
    public void test_any_expression_permutation() {

        List<List<String>> permutations =
                Generator.permutation(new String[] { "x", "x^2", "x+1" })
                        .simple()
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(permutations).hasSize(6);

        System.out.println("Identical permutations of (x, x^2, x+1):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsExactly("x", "x^2", "x+1");
        assertThat(permutations.get(1)).containsExactly("x", "x+1", "x^2");
        assertThat(permutations.get(2)).containsExactly("x+1", "x", "x^2");
        assertThat(permutations.get(3)).containsExactly("x+1", "x^2", "x");
        assertThat(permutations.get(4)).containsExactly("x^2", "x+1", "x");
        assertThat(permutations.get(5)).containsExactly("x^2", "x", "x+1");

    }

    @Test
    public void test_simple_with_equal_elements_permutation() {

        List<List<Integer>> permutations =
                Generator.permutation(Arrays.asList(1, 2, 2, 3))
                        .simple()
                        .stream()
                        .collect(Collectors.<List<Integer>>toList());

        assertThat(permutations).hasSize(12);

        System.out.println("Identical permutations of (1, 2, 2, 3):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsExactly(1, 2, 2, 3);
        assertThat(permutations.get(1)).containsExactly(1, 2, 3, 2);
        assertThat(permutations.get(2)).containsExactly(1, 3, 2, 2);
        assertThat(permutations.get(3)).containsExactly(2, 1, 2, 3);
        assertThat(permutations.get(4)).containsExactly(2, 1, 3, 2);
        assertThat(permutations.get(5)).containsExactly(2, 2, 1, 3);
        assertThat(permutations.get(6)).containsExactly(2, 2, 3, 1);
        assertThat(permutations.get(7)).containsExactly(2, 3, 1, 2);
        assertThat(permutations.get(8)).containsExactly(2, 3, 2, 1);
        assertThat(permutations.get(9)).containsExactly(3, 1, 2, 2);
        assertThat(permutations.get(10)).containsExactly(3, 2, 1, 2);
        assertThat(permutations.get(11)).containsExactly(3, 2, 2, 1);

    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_simple_permutation_iterator_remove_operation() {

        Iterator<List<Integer>> permutations =
                Generator.permutation(Arrays.asList(1, 2, 3))
                        .simple()
                        .iterator();

        assertThat(permutations).isNotNull();
        assertThat(permutations.hasNext()).isTrue();

        // this method should throw a UnsupportedOperationException
        permutations.remove();
    }

    @Test
    public void test_simple_permutations_iterator_toString() {

        Iterator<List<Integer>> permutations =
                Generator.permutation(Arrays.asList(1, 2, 3))
                        .simple()
                        .iterator();

        assertThat(permutations).isNotNull();
        assertThat(permutations.hasNext()).isTrue();
        assertThat(permutations.next()).containsExactly(1, 2, 3);
        assertThat(permutations.toString()).isEqualTo("SimplePermutationIterator=[#1, [1, 2, 3]]");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_duplicated_permutation_iterator_remove_operation() {

        Iterator<List<Integer>> permutations =
                Generator.permutation(Arrays.asList(1, 2, 2))
                        .simple()
                        .iterator();

        assertThat(permutations).isNotNull();
        assertThat(permutations.hasNext()).isTrue();

        // this method should throw a UnsupportedOperationException
        permutations.remove();
    }

    @Test
    public void test_duplicated_permutations_iterator_toString() {

        Iterator<List<Integer>> permutations =
                Generator.permutation(Arrays.asList(1, 2, 2))
                        .simple()
                        .iterator();

        assertThat(permutations).isNotNull();
        assertThat(permutations.hasNext()).isTrue();
        assertThat(permutations.next()).containsExactly(1, 2, 2);
        assertThat(permutations.toString()).isEqualTo("DuplicatedPermutationIterator=[#1, [1, 2, 2]]");
        assertThat(permutations.next()).containsExactly(2, 1, 2);
        assertThat(permutations.toString()).isEqualTo("DuplicatedPermutationIterator=[#2, [2, 1, 2]]");

    }

}
