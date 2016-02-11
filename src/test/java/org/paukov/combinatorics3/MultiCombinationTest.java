package org.paukov.combinatorics3;

import org.junit.Test;

import java.util.Arrays;
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
                        .collect(Collectors.<List<String>>toList());

        assertThat(combinations).hasSize(10);

        System.out.println("Multi combinations 3 of 3 symbols (A,B,C):");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsSequence("A", "A", "A");
        assertThat(combinations.get(1)).containsSequence("A", "A", "B");
        assertThat(combinations.get(2)).containsSequence("A", "A", "C");
        assertThat(combinations.get(3)).containsSequence("A", "B", "B");
        assertThat(combinations.get(4)).containsSequence("A", "B", "C");
        assertThat(combinations.get(5)).containsSequence("A", "C", "C");
        assertThat(combinations.get(6)).containsSequence("B", "B", "B");
        assertThat(combinations.get(7)).containsSequence("B", "B", "C");
        assertThat(combinations.get(8)).containsSequence("B", "C", "C");
        assertThat(combinations.get(9)).containsSequence("C", "C", "C");

    }


    @Test
    public void test_multi_3_combination_of_3_symbols_from_list() {

        List<List<String>> combinations =
                Generator.combination(Arrays.asList("A", "B", "C"))
                        .multi(3)
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(combinations).hasSize(10);

        System.out.println("Multi combinations 3 of 3 symbols (A,B,C):");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsSequence("A", "A", "A");
        assertThat(combinations.get(1)).containsSequence("A", "A", "B");
        assertThat(combinations.get(2)).containsSequence("A", "A", "C");
        assertThat(combinations.get(3)).containsSequence("A", "B", "B");
        assertThat(combinations.get(4)).containsSequence("A", "B", "C");
        assertThat(combinations.get(5)).containsSequence("A", "C", "C");
        assertThat(combinations.get(6)).containsSequence("B", "B", "B");
        assertThat(combinations.get(7)).containsSequence("B", "B", "C");
        assertThat(combinations.get(8)).containsSequence("B", "C", "C");
        assertThat(combinations.get(9)).containsSequence("C", "C", "C");

    }


    @Test
    public void test_multi_3_combination_of_3_symbols_from_array() {

        List<List<String>> combinations =
                Generator.combination(new String[]{"A", "B", "C"})
                        .multi(3)
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(combinations).hasSize(10);

        System.out.println("Multi combinations 3 of 3 symbols (A,B,C):");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsSequence("A", "A", "A");
        assertThat(combinations.get(1)).containsSequence("A", "A", "B");
        assertThat(combinations.get(2)).containsSequence("A", "A", "C");
        assertThat(combinations.get(3)).containsSequence("A", "B", "B");
        assertThat(combinations.get(4)).containsSequence("A", "B", "C");
        assertThat(combinations.get(5)).containsSequence("A", "C", "C");
        assertThat(combinations.get(6)).containsSequence("B", "B", "B");
        assertThat(combinations.get(7)).containsSequence("B", "B", "C");
        assertThat(combinations.get(8)).containsSequence("B", "C", "C");
        assertThat(combinations.get(9)).containsSequence("C", "C", "C");

    }


    @Test
    public void test_multi_0_combination_of_5_colors() {

        List<List<String>> combinations =
                Generator.combination("red", "black", "white", "green", "blue")
                        .multi(0)
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(combinations).hasSize(1);

        System.out.println("Multi combinations 0 of 5 colors:");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).isEmpty();
    }



    @Test
    public void test_multi_3_combination_of_1_colors() {

        List<List<String>> combinations =
                Generator.combination("red")
                        .multi(3)
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(combinations).hasSize(1);

        System.out.println("Multi combinations 3 of 1 color 'red':");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsSequence("red", "red", "red");
    }
}
