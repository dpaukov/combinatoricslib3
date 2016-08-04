package org.paukov.combinatorics3;


import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;


public class SimpleSubSetTest {

    @Test
    public void test_simple_3_sub_set() {

        List<List<String>> subset =
                Generator.subset("red", "black", "white")
                        .simple()
                        .stream()
                        .collect(Collectors.<List<String>>toList());

        assertThat(subset).hasSize(8);

        System.out.println("Simple subset:");
        subset.stream().forEach(System.out::println);

        assertThat(subset.get(0)).isEmpty();
        assertThat(subset.get(1)).containsOnly("red");
        assertThat(subset.get(2)).containsOnly("black");
        assertThat(subset.get(3)).containsSequence("red", "black");
        assertThat(subset.get(4)).containsOnly("white");
        assertThat(subset.get(5)).containsSequence("red", "white");
        assertThat(subset.get(6)).containsSequence("black", "white");
        assertThat(subset.get(7)).containsSequence("red", "black", "white");

    }

}
