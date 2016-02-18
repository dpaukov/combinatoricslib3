/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import org.junit.Test;

import java.util.Arrays;
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

        assertThat(permutations.get(0)).containsSequence(1, 2, 3);
        assertThat(permutations.get(1)).containsSequence(1, 3, 2);
        assertThat(permutations.get(2)).containsSequence(3, 1, 2);
        assertThat(permutations.get(3)).containsSequence(3, 2, 1);
        assertThat(permutations.get(4)).containsSequence(2, 3, 1);
        assertThat(permutations.get(5)).containsSequence(2, 1, 3);

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

        assertThat(permutations.get(0)).containsSequence("a", "a", "b");
        assertThat(permutations.get(1)).containsSequence("a", "b", "a");
        assertThat(permutations.get(2)).containsSequence("b", "a", "a");

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

        assertThat(permutations.get(0)).containsSequence("a", "a", "a");
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

        assertThat(permutations.get(0)).containsSequence("a", "a", "b");
        assertThat(permutations.get(1)).containsSequence("a", "b", "a");
        assertThat(permutations.get(2)).containsSequence("b", "a", "a");
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

        assertThat(permutations.get(0)).containsSequence("a", "a", "b");
        assertThat(permutations.get(1)).containsSequence("a", "b", "a");
        assertThat(permutations.get(2)).containsSequence("b", "a", "a");
        assertThat(permutations.get(3)).containsSequence("b", "a", "a");
        assertThat(permutations.get(4)).containsSequence("a", "b", "a");
        assertThat(permutations.get(5)).containsSequence("a", "a", "b");
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

        assertThat(permutations.get(0)).containsSequence("x", "x", "x");
        assertThat(permutations.get(1)).containsSequence("x", "x", "x");
        assertThat(permutations.get(2)).containsSequence("x", "x", "x");
        assertThat(permutations.get(3)).containsSequence("x", "x", "x");
        assertThat(permutations.get(4)).containsSequence("x", "x", "x");
        assertThat(permutations.get(5)).containsSequence("x", "x", "x");

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

        assertThat(permutations.get(0)).containsSequence("a", "b", "c");
        assertThat(permutations.get(1)).containsSequence("a", "c", "b");
        assertThat(permutations.get(2)).containsSequence("c", "a", "b");
        assertThat(permutations.get(3)).containsSequence("c", "b", "a");
        assertThat(permutations.get(4)).containsSequence("b", "c", "a");
        assertThat(permutations.get(5)).containsSequence("b", "a", "c");

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

        assertThat(permutations.get(0)).containsSequence("x", "x^2", "x+1");
        assertThat(permutations.get(1)).containsSequence("x", "x+1", "x^2");
        assertThat(permutations.get(2)).containsSequence("x+1", "x", "x^2");
        assertThat(permutations.get(3)).containsSequence("x+1", "x^2", "x");
        assertThat(permutations.get(4)).containsSequence("x^2", "x+1", "x");
        assertThat(permutations.get(5)).containsSequence("x^2", "x", "x+1");

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

        assertThat(permutations.get(0)).containsSequence(1, 2, 2, 3);
        assertThat(permutations.get(1)).containsSequence(1, 2, 3, 2);
        assertThat(permutations.get(2)).containsSequence(1, 3, 2, 2);
        assertThat(permutations.get(3)).containsSequence(2, 1, 2, 3);
        assertThat(permutations.get(4)).containsSequence(2, 1, 3, 2);
        assertThat(permutations.get(5)).containsSequence(2, 2, 1, 3);
        assertThat(permutations.get(6)).containsSequence(2, 2, 3, 1);
        assertThat(permutations.get(7)).containsSequence(2, 3, 1, 2);
        assertThat(permutations.get(8)).containsSequence(2, 3, 2, 1);
        assertThat(permutations.get(9)).containsSequence(3, 1, 2, 2);
        assertThat(permutations.get(10)).containsSequence(3, 2, 1, 2);
        assertThat(permutations.get(11)).containsSequence(3, 2, 2, 1);

    }


}
