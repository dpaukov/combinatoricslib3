/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;


public final class SimpleCombinationTest {

  @Test
  public void test_simple_3_combination_of_5_colors() {
    List<List<String>> combinations =
        Generator.combination("red", "black", "white", "green", "blue")
            .simple(3)
            .stream()
            .collect(toList());

    assertThat(combinations).hasSize(10);

    System.out.println("Simple combinations 3 of 5 colors:");
    combinations.stream().forEach(System.out::println);

    assertThat(combinations.get(0)).containsSequence("red", "black", "white");
    assertThat(combinations.get(1)).containsSequence("red", "black", "green");
    assertThat(combinations.get(2)).containsSequence("red", "black", "blue");
    assertThat(combinations.get(3)).containsSequence("red", "white", "green");
    assertThat(combinations.get(4)).containsSequence("red", "white", "blue");
    assertThat(combinations.get(5)).containsSequence("red", "green", "blue");
    assertThat(combinations.get(6)).containsSequence("black", "white", "green");
    assertThat(combinations.get(7)).containsSequence("black", "white", "blue");
    assertThat(combinations.get(8)).containsSequence("black", "green", "blue");
    assertThat(combinations.get(9)).containsSequence("white", "green", "blue");
  }


  @Test
  public void test_simple_2_combination_of_3_numbers() {
    List<List<Integer>> combinations = Generator.combination(0, 1, 2)
        .simple(2)
        .stream()
        .collect(toList());

    assertThat(combinations).hasSize(3);

    System.out.println("Simple combinations 2 of the integers (0, 1, 2):");
    combinations.stream().forEach(System.out::println);

    assertThat(combinations.get(0)).containsExactly(0, 1);
    assertThat(combinations.get(1)).containsExactly(0, 2);
    assertThat(combinations.get(2)).containsExactly(1, 2);

  }

  @Test
  public void test_simple_0_combination_of_5_colors() {
    List<List<String>> combinations = Generator
        .combination("red", "black", "white", "green", "blue")
        .simple(0)
        .stream()
        .collect(toList());

    assertThat(combinations).hasSize(1);

    System.out.println("Simple combinations 0 of 5 colors:");
    combinations.stream().forEach(System.out::println);

    assertThat(combinations.get(0)).isEmpty();
  }

  @Test
  public void test_simple_2_combination_of_3_numbers_from_integer_array() {
    List<List<Integer>> combinations =
        Generator.combination(new Integer[]{0, 1, 2})
            .simple(2)
            .stream()
            .collect(toList());

    assertThat(combinations).hasSize(3);

    System.out.println("Simple combinations 2 of the integers (0, 1, 2):");
    combinations.stream().forEach(System.out::println);

    assertThat(combinations.get(0)).containsExactly(0, 1);
    assertThat(combinations.get(1)).containsExactly(0, 2);
    assertThat(combinations.get(2)).containsExactly(1, 2);

  }

  @Test
  public void test_simple_2_combination_of_3_numbers_from_integer_list() {
    List<List<Integer>> combinations =
        Generator.combination(asList(0, 1, 2))
            .simple(2)
            .stream()
            .collect(toList());

    assertThat(combinations).hasSize(3);

    System.out.println("Simple combinations 2 of the integers (0, 1, 2):");
    combinations.stream().forEach(System.out::println);

    assertThat(combinations.get(0)).containsExactly(0, 1);
    assertThat(combinations.get(1)).containsExactly(0, 2);
    assertThat(combinations.get(2)).containsExactly(1, 2);

  }


  @Test
  public void test_simple_combination_iterator_remove_operation() {
    Iterator<List<Integer>> combinations =
        Generator.combination(asList(0, 1, 2))
            .simple(2)
            .iterator();

    assertThat(combinations).isNotNull();
    assertThat(combinations.hasNext()).isTrue();

    // this method should throw a UnsupportedOperationException
    assertThrows(UnsupportedOperationException.class, combinations::remove);
  }

  @Test
  public void test_simple_combination_iterator_toString() {
    Iterator<List<Integer>> combinations =
        Generator.combination(asList(0, 1, 2))
            .simple(2)
            .iterator();

    assertThat(combinations).isNotNull();
    assertThat(combinations.hasNext()).isTrue();
    assertThat(combinations.next()).contains(0, 1);
    assertThat(combinations.toString()).isEqualTo("SimpleCombinationIterator=[#1, [0, 1]]");
  }
}
