/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;

public class PermutationsWithRepetitions {

    @Test
    public void test_simple_permutation_with_repetition() {

        List<List<Integer>> permutations =
                Generator.permutation(1, 2, 3)
                        .withRepetitions(2)
                        .stream()
                        .collect(Collectors.<List<Integer>>toList());

        assertThat(permutations).hasSize(9);

        System.out.println("Simple permutations with repetitions of (1, 2, 3):");
        permutations.stream().forEach(System.out::println);

        assertThat(permutations.get(0)).containsSequence(1, 1);
        assertThat(permutations.get(1)).containsSequence(2, 1);
        assertThat(permutations.get(2)).containsSequence(3, 1);
        assertThat(permutations.get(3)).containsSequence(1, 2);
        assertThat(permutations.get(4)).containsSequence(2, 2);
        assertThat(permutations.get(5)).containsSequence(3, 2);
        assertThat(permutations.get(6)).containsSequence(1, 3);
        assertThat(permutations.get(7)).containsSequence(2, 3);
        assertThat(permutations.get(8)).containsSequence(3, 3);

    }
}
