package org.paukov.combinatorics3;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

@SuppressWarnings("unchecked")
public class SimpleCombinationFromListTest {

    @Test
    public void test_simple_combination_from_list_number() {

        List<List<Integer>> combinations =
                Generator.combinationFromList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6))
                    .stream()
                    .collect(Collectors.<List<Integer>>toList());

        assertThat(combinations).hasSize(9);

        System.out.println("List combinations of lists (number) - (1, 2, 3), (4, 5, 6)");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsSequence(1, 4);
        assertThat(combinations.get(1)).containsSequence(1, 5);
        assertThat(combinations.get(2)).containsSequence(1, 6);
        assertThat(combinations.get(3)).containsSequence(2, 4);
        assertThat(combinations.get(4)).containsSequence(2, 5);
        assertThat(combinations.get(5)).containsSequence(2, 6);
        assertThat(combinations.get(6)).containsSequence(3, 4);
        assertThat(combinations.get(7)).containsSequence(3, 5);
        assertThat(combinations.get(8)).containsSequence(3, 6);
    }
    
    @Test
    public void test_simple_combination_from_list_string() {

        List<List<String>> combinations =
                Generator.combinationFromList(Arrays.asList("banana", "apple"), 
                        Arrays.asList("melon", "orange", "lemon"), Arrays.asList("peach", "blueberry"))
                    .stream()
                    .collect(Collectors.<List<String>>toList());

        assertThat(combinations).hasSize(12);

        System.out.print("List combinations of lists (string) - ");
        System.out.println("(\"banana\", \"apple\"), (\"melon\", \"orange\", \"lemon\"), (\"peach\", \"blueberry\")");
        combinations.stream().forEach(System.out::println);

        assertThat(combinations.get(0)).containsSequence("banana", "melon", "peach");
        assertThat(combinations.get(1)).containsSequence("banana", "melon", "blueberry");
        assertThat(combinations.get(2)).containsSequence("banana", "orange", "peach");
        assertThat(combinations.get(3)).containsSequence("banana", "orange", "blueberry");
        assertThat(combinations.get(4)).containsSequence("banana", "lemon", "peach");
        assertThat(combinations.get(5)).containsSequence("banana", "lemon", "blueberry");
        assertThat(combinations.get(6)).containsSequence("apple", "melon", "peach");
        assertThat(combinations.get(7)).containsSequence("apple", "melon", "blueberry");
        assertThat(combinations.get(8)).containsSequence("apple", "orange", "peach");
        assertThat(combinations.get(9)).containsSequence("apple", "orange", "blueberry");
        assertThat(combinations.get(10)).containsSequence("apple", "lemon", "peach");
        assertThat(combinations.get(11)).containsSequence("apple", "lemon", "blueberry");
    }
    
    @Test
    public void test_simple_combination_from_list_iterator_toString() {
        Iterator<List<Integer>> combinations = 
            Generator.combinationFromList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)).iterator();

        assertThat(combinations).isNotNull();
        assertThat(combinations.hasNext()).isTrue();
        assertThat(combinations.next()).containsExactly(1, 4);
        assertThat(combinations.toString()).isEqualTo("SimpleCombinationOfListsIterator=[#1, [1, 4]]");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_simple_combination_from_list_iterator_remove_operation() {
        Iterator<List<Integer>> combinations = 
            Generator.combinationFromList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)).iterator();

        assertThat(combinations).isNotNull();
        assertThat(combinations.hasNext()).isTrue();

        // this method should throw a UnsupportedOperationException
        combinations.remove();
    }

    @Test(expected = RuntimeException.class)
    public void test_simple_combination_from_list_no_empty() {
        Generator.combinationFromList(Arrays.asList());
    }
}
