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

public class MultiCombinationTest {

    @Test
    public void test_multi_3_combination_of_3_symbols() {

        List<List<String>> combinations =
                Generator.combination("A", "B", "C")
                        .multi(3)
                        .stream()
                        .collect(Collectors.toList());

        assertThat(combinations).hasSize(10);

        System.out.println("Multi combinations 3 of 3 symbols (A,B,C):");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsExactly("A", "A", "A");
        assertThat(combinations.get(1)).containsExactly("A", "A", "B");
        assertThat(combinations.get(2)).containsExactly("A", "A", "C");
        assertThat(combinations.get(3)).containsExactly("A", "B", "B");
        assertThat(combinations.get(4)).containsExactly("A", "B", "C");
        assertThat(combinations.get(5)).containsExactly("A", "C", "C");
        assertThat(combinations.get(6)).containsExactly("B", "B", "B");
        assertThat(combinations.get(7)).containsExactly("B", "B", "C");
        assertThat(combinations.get(8)).containsExactly("B", "C", "C");
        assertThat(combinations.get(9)).containsExactly("C", "C", "C");

    }

    @Test
    public void test_multi_3_combination_of_2_symbols() {

        List<List<String>> combinations =
                Generator.combination("A", "B")
                        .multi(3)
                        .stream()
                        .collect(Collectors.toList());

        assertThat(combinations).hasSize(4);

        System.out.println("Multi combinations 3 of 2 symbols (A,B):");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsExactly("A", "A", "A");
        assertThat(combinations.get(1)).containsExactly("A", "A", "B");
        assertThat(combinations.get(2)).containsExactly("A", "B", "B");
        assertThat(combinations.get(3)).containsExactly("B", "B", "B");

    }

    @Test
    public void test_multi_3_combination_of_3_symbols_from_list() {

        List<List<String>> combinations =
                Generator.combination(Arrays.asList("A", "B", "C"))
                        .multi(3)
                        .stream()
                        .collect(Collectors.toList());

        assertThat(combinations).hasSize(10);

        System.out.println("Multi combinations 3 of 3 symbols (A,B,C):");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsExactly("A", "A", "A");
        assertThat(combinations.get(1)).containsExactly("A", "A", "B");
        assertThat(combinations.get(2)).containsExactly("A", "A", "C");
        assertThat(combinations.get(3)).containsExactly("A", "B", "B");
        assertThat(combinations.get(4)).containsExactly("A", "B", "C");
        assertThat(combinations.get(5)).containsExactly("A", "C", "C");
        assertThat(combinations.get(6)).containsExactly("B", "B", "B");
        assertThat(combinations.get(7)).containsExactly("B", "B", "C");
        assertThat(combinations.get(8)).containsExactly("B", "C", "C");
        assertThat(combinations.get(9)).containsExactly("C", "C", "C");

    }


    @Test
    public void test_multi_3_combination_of_3_symbols_from_array() {

        List<List<String>> combinations =
                Generator.combination(new String[]{"A", "B", "C"})
                        .multi(3)
                        .stream()
                        .collect(Collectors.toList());

        assertThat(combinations).hasSize(10);

        System.out.println("Multi combinations 3 of 3 symbols (A,B,C):");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsExactly("A", "A", "A");
        assertThat(combinations.get(1)).containsExactly("A", "A", "B");
        assertThat(combinations.get(2)).containsExactly("A", "A", "C");
        assertThat(combinations.get(3)).containsExactly("A", "B", "B");
        assertThat(combinations.get(4)).containsExactly("A", "B", "C");
        assertThat(combinations.get(5)).containsExactly("A", "C", "C");
        assertThat(combinations.get(6)).containsExactly("B", "B", "B");
        assertThat(combinations.get(7)).containsExactly("B", "B", "C");
        assertThat(combinations.get(8)).containsExactly("B", "C", "C");
        assertThat(combinations.get(9)).containsExactly("C", "C", "C");

    }


    @Test
    public void test_multi_0_combination_of_5_colors() {

        List<List<String>> combinations =
                Generator.combination("red", "black", "white", "green", "blue")
                        .multi(0)
                        .stream()
                        .collect(Collectors.toList());

        assertThat(combinations).hasSize(1);

        System.out.println("Multi combinations 0 of 5 colors:");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).isEmpty();
    }

    @Test
    public void test_multi_negative_combination_of_3_colors() {

        List<List<String>> combinations =
                Generator.combination("red", "black", "white")
                        .multi(-2)
                        .stream()
                        .collect(Collectors.toList());

        assertThat(combinations).hasSize(1);

        System.out.println("Multi combinations -2 of 3 colors:");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).isEmpty();
    }


    @Test
    public void test_multi_3_combination_of_1_colors() {

        List<List<String>> combinations =
                Generator.combination("red")
                        .multi(3)
                        .stream()
                        .collect(Collectors.toList());

        assertThat(combinations).hasSize(1);

        System.out.println("Multi combinations 3 of 1 color 'red':");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsExactly("red", "red", "red");
    }


    @Test(expected = UnsupportedOperationException.class)
    public void test_multi_combination_iterator_remove_operation() {

        Iterator<List<Integer>> combinations =
                Generator.combination(Arrays.asList(0, 1, 2))
                        .multi(2)
                        .iterator();

        assertThat(combinations).isNotNull();
        assertThat(combinations.hasNext()).isTrue();

        // this method should throw a UnsupportedOperationException
        combinations.remove();
    }

    @Test
    public void test_multi_combination_iterator_toString() {

        Iterator<List<Integer>> combinations =
                Generator.combination(Arrays.asList(0, 1, 2))
                        .multi(2)
                        .iterator();

        assertThat(combinations).isNotNull();
        assertThat(combinations.hasNext()).isTrue();
        assertThat(combinations.next()).containsExactly(0, 0);
        assertThat(combinations.toString()).isEqualTo("MultiCombinationIterator=[#1, [0, 0]]");
    }
}
