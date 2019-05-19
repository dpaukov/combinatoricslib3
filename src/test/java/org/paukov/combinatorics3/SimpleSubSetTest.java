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


public final class SimpleSubSetTest {

  @Test
  public void test_simple_3_sub_set() {
    List<List<String>> subset =
        Generator.subset("red", "black", "white")
            .simple()
            .stream()
            .collect(toList());

    assertThat(subset).hasSize(8);

    System.out.println("Simple subset:");
    subset.stream().forEach(System.out::println);

    assertThat(subset.get(0)).isEmpty();
    assertThat(subset.get(1)).containsOnly("red");
    assertThat(subset.get(2)).containsOnly("black");
    assertThat(subset.get(3)).containsExactly("red", "black");
    assertThat(subset.get(4)).containsOnly("white");
    assertThat(subset.get(5)).containsExactly("red", "white");
    assertThat(subset.get(6)).containsExactly("black", "white");
    assertThat(subset.get(7)).containsExactly("red", "black", "white");
  }

  @Test
  public void test_empty_sub_set() {
    List<List<String>> subset =
        Generator.subset(new String[0])
            .simple()
            .stream()
            .collect(toList());

    assertThat(subset).hasSize(1);

    System.out.println("Empty subset:");
    subset.stream().forEach(System.out::println);

    assertThat(subset.get(0)).isEmpty();
  }


  @Test
  public void test_simple_sub_set_with_duplicates() {
    List<List<String>> subset =
        Generator.subset("A", "A", "B", "B", "C")
            .simple()
            .stream()
            .collect(toList());

    assertThat(subset).hasSize(32);

    System.out.println("Simple subset with duplicates:");
    subset.stream().forEach(System.out::println);

    assertThat(subset.get(0)).isEmpty();
    assertThat(subset.get(1)).containsOnly("A");
    assertThat(subset.get(2)).containsOnly("A");
    assertThat(subset.get(3)).containsExactly("A", "A");
    assertThat(subset.get(4)).containsOnly("B");
    assertThat(subset.get(5)).containsExactly("A", "B");
    assertThat(subset.get(6)).containsExactly("A", "B");
    assertThat(subset.get(7)).containsExactly("A", "A", "B");
    assertThat(subset.get(8)).containsOnly("B");
    assertThat(subset.get(9)).containsExactly("A", "B");
    assertThat(subset.get(10)).containsExactly("A", "B");
    assertThat(subset.get(11)).containsExactly("A", "A", "B");
    assertThat(subset.get(12)).containsExactly("B", "B");
    assertThat(subset.get(13)).containsExactly("A", "B", "B");
    assertThat(subset.get(14)).containsExactly("A", "B", "B");
    assertThat(subset.get(15)).containsExactly("A", "A", "B", "B");
    assertThat(subset.get(16)).containsOnly("C");
    assertThat(subset.get(17)).containsExactly("A", "C");
    assertThat(subset.get(18)).containsExactly("A", "C");
    assertThat(subset.get(19)).containsExactly("A", "A", "C");
    assertThat(subset.get(20)).containsExactly("B", "C");
    assertThat(subset.get(21)).containsExactly("A", "B", "C");
    assertThat(subset.get(22)).containsExactly("A", "B", "C");
    assertThat(subset.get(23)).containsExactly("A", "A", "B", "C");
    assertThat(subset.get(24)).containsExactly("B", "C");
    assertThat(subset.get(25)).containsExactly("A", "B", "C");
    assertThat(subset.get(26)).containsExactly("A", "B", "C");
    assertThat(subset.get(27)).containsExactly("A", "A", "B", "C");
    assertThat(subset.get(28)).containsExactly("B", "B", "C");
    assertThat(subset.get(29)).containsExactly("A", "B", "B", "C");
    assertThat(subset.get(30)).containsExactly("A", "B", "B", "C");
    assertThat(subset.get(31)).containsExactly("A", "A", "B", "B", "C");
  }


  @Test
  public void test_simple_distinct_sub_set_with_duplicates() {
    List<List<String>> subset =
        Generator.subset("A", "A", "B", "B", "C")
            .simple()
            .stream()
            .distinct()
            .collect(toList());

    assertThat(subset).hasSize(18);

    System.out.println("Simple distinct subset with duplicates:");
    subset.stream().forEach(System.out::println);

    assertThat(subset.get(0)).isEmpty();
    assertThat(subset.get(1)).containsOnly("A");
    assertThat(subset.get(2)).containsExactly("A", "A");
    assertThat(subset.get(3)).containsOnly("B");
    assertThat(subset.get(4)).containsExactly("A", "B");
    assertThat(subset.get(5)).containsExactly("A", "A", "B");
    assertThat(subset.get(6)).containsExactly("B", "B");
    assertThat(subset.get(7)).containsExactly("A", "B", "B");
    assertThat(subset.get(8)).containsExactly("A", "A", "B", "B");
    assertThat(subset.get(9)).containsOnly("C");
    assertThat(subset.get(10)).containsExactly("A", "C");
    assertThat(subset.get(11)).containsExactly("A", "A", "C");
    assertThat(subset.get(12)).containsExactly("B", "C");
    assertThat(subset.get(13)).containsExactly("A", "B", "C");
    assertThat(subset.get(14)).containsExactly("A", "A", "B", "C");
    assertThat(subset.get(15)).containsExactly("B", "B", "C");
    assertThat(subset.get(16)).containsExactly("A", "B", "B", "C");
    assertThat(subset.get(17)).containsExactly("A", "A", "B", "B", "C");
  }

  @Test
  public void test_simple_subset_iterator_remove_operation() {
    Iterator<List<Integer>> subset =
        Generator.subset(asList(1, 2, 3))
            .simple()
            .iterator();

    assertThat(subset).isNotNull();
    assertThat(subset.hasNext()).isTrue();

    // this method should throw a UnsupportedOperationException
    assertThrows(UnsupportedOperationException.class, subset::remove);
  }

  @Test
  public void test_simple_subset_iterator_toString() {
    Iterator<List<Integer>> subset =
        Generator.subset(asList(1, 2, 3))
            .simple()
            .iterator();

    assertThat(subset).isNotNull();
    assertThat(subset.hasNext()).isTrue();
    assertThat(subset.next()).isEmpty();
    assertThat(subset.toString()).isEqualTo("SimpleSubSetIterator=[#1, []]");
  }
}
