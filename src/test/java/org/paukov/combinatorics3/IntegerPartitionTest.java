/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;

public class IntegerPartitionTest {

    @Test
    public void test_integer_partition_of_5() {

        List<List<Integer>> partition =
                Generator.partition(5)
                        .stream()
                        .collect(Collectors.<List<Integer>>toList());

        assertThat(partition).hasSize(7);

        System.out.println("Integer partitions of 5:");
        partition.stream().forEach(System.out::println);

        assertThat(partition.get(0)).containsExactly(1, 1, 1, 1, 1);
        assertThat(partition.get(1)).containsExactly(2, 1, 1, 1);
        assertThat(partition.get(2)).containsExactly(2, 2, 1);
        assertThat(partition.get(3)).containsExactly(3, 1, 1);
        assertThat(partition.get(4)).containsExactly(3, 2);
        assertThat(partition.get(5)).containsExactly(4, 1);
        assertThat(partition.get(6)).containsOnly(5);


    }
}
