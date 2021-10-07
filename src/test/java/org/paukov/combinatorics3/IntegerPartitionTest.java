/**
 * Combinatorics Library 3 Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import static java.util.stream.Collectors.toList;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;


public final class IntegerPartitionTest {

  @Test
  public void test_integer_partition_of_5() {
    List<List<Integer>> partition = Generator.partition(5)
        .stream()
        .collect(toList());

    assertThat(partition).hasSize(7);
    assertThat(partition.get(0)).containsExactly(1, 1, 1, 1, 1);
    assertThat(partition.get(1)).containsExactly(2, 1, 1, 1);
    assertThat(partition.get(2)).containsExactly(2, 2, 1);
    assertThat(partition.get(3)).containsExactly(3, 1, 1);
    assertThat(partition.get(4)).containsExactly(3, 2);
    assertThat(partition.get(5)).containsExactly(4, 1);
    assertThat(partition.get(6)).containsOnly(5);
  }

  @Test
  public void test_integer_partition_of_1() {
    List<List<Integer>> partition = Generator.partition(1)
        .stream()
        .collect(toList());

    assertThat(partition).hasSize(1);
    assertThat(partition.get(0)).containsOnly(1);
  }

  @Test
  public void test_integer_partition_of_0() {
    List<List<Integer>> partition = Generator.partition(0)
        .stream()
        .collect(toList());

    assertThat(partition).isEmpty();
  }

  @Test
  public void test_integer_partition_of_negative() {
    List<List<Integer>> partition = Generator.partition(-1)
        .stream()
        .collect(toList());

    assertThat(partition).isEmpty();
  }

  @Test
  public void test_integer_partition_iterator_remove_operation() {
    Iterator<List<Integer>> partition = Generator.partition(5).iterator();

    assertThat(partition).isNotNull();
    assertThat(partition.hasNext()).isTrue();
    // this method should throw a UnsupportedOperationException
    assertThrows(UnsupportedOperationException.class, partition::remove);
  }

  @Test
  public void test_integer_partition_iterator_toString() {
    Iterator<List<Integer>> partition = Generator.partition(5).iterator();

    assertThat(partition).isNotNull();
    assertThat(partition.hasNext()).isTrue();
    assertThat(partition.next()).containsExactly(1, 1, 1, 1, 1);
    assertThat(partition.toString()).isEqualTo("IntegerPartitionIterator=[#1, [1, 1, 1, 1, 1]]");
  }
}
