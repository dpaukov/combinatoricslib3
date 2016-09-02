package org.paukov.combinatorics3;


import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
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

    @Test
    public void test_empty_sub_set() {

        List<List<String>> subset =
                Generator.subset(new String[0])
                        .simple()
                        .stream()
                        .collect(Collectors.<List<String>>toList());

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
                        .collect(Collectors.<List<String>>toList());

        assertThat(subset).hasSize(32);

        System.out.println("Simple subset with duplicates:");
        subset.stream().forEach(System.out::println);

        assertThat(subset.get(0)).isEmpty();
        assertThat(subset.get(1)).containsOnly("A");
        assertThat(subset.get(2)).containsOnly("A");
        assertThat(subset.get(3)).containsSequence("A", "A");
        assertThat(subset.get(4)).containsOnly("B");
        assertThat(subset.get(5)).containsSequence("A", "B");
        assertThat(subset.get(6)).containsSequence("A", "B");
        assertThat(subset.get(7)).containsSequence("A", "A", "B");
        assertThat(subset.get(8)).containsOnly("B");
        assertThat(subset.get(9)).containsSequence("A", "B");
        assertThat(subset.get(10)).containsSequence("A", "B");
        assertThat(subset.get(11)).containsSequence("A", "A", "B");
        assertThat(subset.get(12)).containsSequence("B", "B");
        assertThat(subset.get(13)).containsSequence("A", "B", "B");
        assertThat(subset.get(14)).containsSequence("A", "B", "B");
        assertThat(subset.get(15)).containsSequence("A", "A", "B", "B");
        assertThat(subset.get(16)).containsOnly("C");
        assertThat(subset.get(17)).containsSequence("A", "C");
        assertThat(subset.get(18)).containsSequence("A", "C");
        assertThat(subset.get(19)).containsSequence("A", "A", "C");
        assertThat(subset.get(20)).containsSequence("B", "C");
        assertThat(subset.get(21)).containsSequence("A", "B", "C");
        assertThat(subset.get(22)).containsSequence("A", "B", "C");
        assertThat(subset.get(23)).containsSequence("A", "A", "B", "C");
        assertThat(subset.get(24)).containsSequence("B", "C");
        assertThat(subset.get(25)).containsSequence("A", "B", "C");
        assertThat(subset.get(26)).containsSequence("A", "B", "C");
        assertThat(subset.get(27)).containsSequence("A", "A", "B", "C");
        assertThat(subset.get(28)).containsSequence("B", "B", "C");
        assertThat(subset.get(29)).containsSequence("A", "B", "B", "C");
        assertThat(subset.get(30)).containsSequence("A", "B", "B", "C");
        assertThat(subset.get(31)).containsSequence("A", "A","B", "B", "C");
    }



    @Test
    public void test_simple_distinct_sub_set_with_duplicates() {

        List<List<String>> subset =
                Generator.subset("A", "A", "B", "B", "C")
                        .simple()
                        .stream()
                        .distinct()
                        .collect(Collectors.<List<String>>toList());

        assertThat(subset).hasSize(18);

        System.out.println("Simple distinct subset with duplicates:");
        subset.stream().forEach(System.out::println);

        assertThat(subset.get(0)).isEmpty();
        assertThat(subset.get(1)).containsOnly("A");
        assertThat(subset.get(2)).containsOnly("A", "A");
        assertThat(subset.get(3)).containsOnly("B");
        assertThat(subset.get(4)).containsSequence("A", "B");
        assertThat(subset.get(5)).containsSequence("A", "A", "B");
        assertThat(subset.get(6)).containsSequence("B", "B");
        assertThat(subset.get(7)).containsSequence("A", "B", "B");
        assertThat(subset.get(8)).containsSequence("A", "A", "B", "B");
        assertThat(subset.get(9)).containsOnly("C");
        assertThat(subset.get(10)).containsSequence("A", "C");
        assertThat(subset.get(11)).containsSequence("A", "A", "C");
        assertThat(subset.get(12)).containsSequence("B", "C");
        assertThat(subset.get(13)).containsSequence("A", "B", "C");
        assertThat(subset.get(14)).containsSequence("A", "A", "B", "C");
        assertThat(subset.get(15)).containsSequence("B", "B", "C");
        assertThat(subset.get(16)).containsSequence("A", "B", "B", "C");
        assertThat(subset.get(17)).containsSequence("A", "A","B", "B", "C");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_simple_subset_iterator_remove_operation() {

        Iterator<List<Integer>> subset =
                Generator.subset(Arrays.asList(1, 2, 3))
                        .simple()
                        .iterator();

        assertThat(subset).isNotNull();
        assertThat(subset.hasNext()).isTrue();

        // this method should throw a UnsupportedOperationException
        subset.remove();
    }

    @Test
    public void test_simple_subset_iterator_toString() {

        Iterator<List<Integer>> subset =
                Generator.subset(Arrays.asList(1, 2, 3))
                        .simple()
                        .iterator();

        assertThat(subset).isNotNull();
        assertThat(subset.hasNext()).isTrue();
        assertThat(subset.next()).isEmpty();
        assertThat(subset.toString()).isEqualTo("SubSetIterator=[#1, []]");
    }
}
