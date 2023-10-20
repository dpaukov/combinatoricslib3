/**
 * Combinatorics Library 3 Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

public final class MultiCombinationTest {

  @Test
  public void test_multi_3_combination_of_3_symbols() {
    List<List<String>> combinations = Generator.combination("A", "B", "C")
        .multi(3)
        .stream()
        .collect(toList());

    assertThat(combinations).hasSize(10);
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
    List<List<String>> combinations = Generator.combination("A", "B")
        .multi(3)
        .stream()
        .collect(toList());

    assertThat(combinations).hasSize(4);
    assertThat(combinations.get(0)).containsExactly("A", "A", "A");
    assertThat(combinations.get(1)).containsExactly("A", "A", "B");
    assertThat(combinations.get(2)).containsExactly("A", "B", "B");
    assertThat(combinations.get(3)).containsExactly("B", "B", "B");
  }

  @Test
  public void test_multi_3_combination_of_3_symbols_from_list() {
    List<List<String>> combinations = Generator.combination(asList("A", "B", "C"))
        .multi(3)
        .stream()
        .collect(toList());

    assertThat(combinations).hasSize(10);
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
    List<List<String>> combinations = Generator.combination(new String[]{"A", "B", "C"})
        .multi(3)
        .stream()
        .collect(toList());

    assertThat(combinations).hasSize(10);
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
    List<List<String>> combinations = Generator
        .combination("red", "black", "white", "green", "blue")
        .multi(0)
        .stream()
        .collect(toList());

    assertThat(combinations).hasSize(1);
    assertThat(combinations.get(0)).isEmpty();
  }

  @Test
  public void test_multi_negative_combination_of_3_colors() {
    List<List<String>> combinations = Generator.combination("red", "black", "white")
        .multi(-2)
        .stream()
        .collect(toList());

    assertThat(combinations).hasSize(1);
    assertThat(combinations.get(0)).isEmpty();
  }


  @Test
  public void test_multi_3_combination_of_1_colors() {
    List<List<String>> combinations = Generator.combination("red")
        .multi(3)
        .stream()
        .collect(toList());

    assertThat(combinations).hasSize(1);
    assertThat(combinations.get(0)).containsExactly("red", "red", "red");
  }

  @Test
  public void test_multi_3_combination_empty() {
    List<List<Object>> combinations = Generator.combination()
        .multi(3)
        .stream()
        .collect(toList());

    assertThat(combinations).hasSize(1);
    assertThat(combinations.get(0)).isEmpty();
  }


  @Test
  public void test_multi_combination_iterator_remove_operation() {
    Iterator<List<Integer>> combinations = Generator.combination(asList(0, 1, 2))
        .multi(2)
        .iterator();

    assertThat(combinations).isNotNull();
    assertThat(combinations.hasNext()).isTrue();
    // This method should throw a UnsupportedOperationException.
    assertThrows(UnsupportedOperationException.class, combinations::remove);
  }

  @Test
  public void test_multi_combination_iterator_toString() {
    Iterator<List<Integer>> combinations = Generator.combination(asList(0, 1, 2))
        .multi(2)
        .iterator();

    assertThat(combinations).isNotNull();
    assertThat(combinations.hasNext()).isTrue();
    assertThat(combinations.next()).containsExactly(0, 0);
    assertThat(combinations.toString()).isEqualTo("MultiCombinationIterator=[#1, [0, 0]]");
  }
}
