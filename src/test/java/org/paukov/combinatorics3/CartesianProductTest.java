package org.paukov.combinatorics3;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class CartesianProductTest {

    @Test
    public void test_cartesian_product_number() {

        List<List<Integer>> cartesianProduct =
                Generator.cartesianProduct(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6))
                    .stream()
                    .collect(Collectors.toList());

        assertThat(cartesianProduct).hasSize(9);

        System.out.println("List cartesian product (number) - (1, 2, 3), (4, 5, 6)");
        cartesianProduct.stream().forEach(System.out::println);

        assertThat(cartesianProduct.get(0)).containsSequence(1, 4);
        assertThat(cartesianProduct.get(1)).containsSequence(1, 5);
        assertThat(cartesianProduct.get(2)).containsSequence(1, 6);
        assertThat(cartesianProduct.get(3)).containsSequence(2, 4);
        assertThat(cartesianProduct.get(4)).containsSequence(2, 5);
        assertThat(cartesianProduct.get(5)).containsSequence(2, 6);
        assertThat(cartesianProduct.get(6)).containsSequence(3, 4);
        assertThat(cartesianProduct.get(7)).containsSequence(3, 5);
        assertThat(cartesianProduct.get(8)).containsSequence(3, 6);
    }
    
    @Test
    public void test_cartesian_product_string() {

        List<List<String>> cartesianProduct =
                Generator.cartesianProduct(Arrays.asList("banana", "apple"), 
                        Arrays.asList("melon", "orange", "lemon"), Arrays.asList("peach", "blueberry"))
                    .stream()
                    .collect(Collectors.<List<String>>toList());

        assertThat(cartesianProduct).hasSize(12);

        System.out.print("List cartesian product (string) - ");
        System.out.println("(\"banana\", \"apple\"), (\"melon\", \"orange\", \"lemon\"), (\"peach\", \"blueberry\")");
        cartesianProduct.stream().forEach(System.out::println);

        assertThat(cartesianProduct.get(0)).containsSequence("banana", "melon", "peach");
        assertThat(cartesianProduct.get(1)).containsSequence("banana", "melon", "blueberry");
        assertThat(cartesianProduct.get(2)).containsSequence("banana", "orange", "peach");
        assertThat(cartesianProduct.get(3)).containsSequence("banana", "orange", "blueberry");
        assertThat(cartesianProduct.get(4)).containsSequence("banana", "lemon", "peach");
        assertThat(cartesianProduct.get(5)).containsSequence("banana", "lemon", "blueberry");
        assertThat(cartesianProduct.get(6)).containsSequence("apple", "melon", "peach");
        assertThat(cartesianProduct.get(7)).containsSequence("apple", "melon", "blueberry");
        assertThat(cartesianProduct.get(8)).containsSequence("apple", "orange", "peach");
        assertThat(cartesianProduct.get(9)).containsSequence("apple", "orange", "blueberry");
        assertThat(cartesianProduct.get(10)).containsSequence("apple", "lemon", "peach");
        assertThat(cartesianProduct.get(11)).containsSequence("apple", "lemon", "blueberry");
    }
    
    @Test
    public void test_cartesian_product_iterator_toString() {
        Iterator<List<Integer>> cartesianProduct = 
            Generator.cartesianProduct(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)).iterator();

        assertThat(cartesianProduct).isNotNull();
        assertThat(cartesianProduct.hasNext()).isTrue();
        assertThat(cartesianProduct.next()).containsExactly(1, 4);
        assertThat(cartesianProduct.toString()).isEqualTo("CartesianProductIterator=[#1, [1, 4]]");
    }
    
    @Test
    public void test_cartesian_product_empty() {
        List<List<Object>> cartesianProduct = 
            Generator.cartesianProduct(Collections.emptyList()).stream()
            .collect(Collectors.toList());
        
        assertThat(cartesianProduct).hasSize(0);
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void test_cartesian_product_iterator_remove_operation() {
        Iterator<List<Integer>> cartesianProduct = 
            Generator.cartesianProduct(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)).iterator();

        assertThat(cartesianProduct).isNotNull();
        assertThat(cartesianProduct.hasNext()).isTrue();

        // this method should throw a UnsupportedOperationException
        cartesianProduct.remove();
    }
    
    @Test(expected = RuntimeException.class)
    public void test_cartesian_product_no_more_cartesian_product() {
        Iterator<List<Integer>> cartesianProduct = 
            Generator.cartesianProduct(Arrays.asList(1), Arrays.asList(4)).iterator();

        assertThat(cartesianProduct).isNotNull();
        assertThat(cartesianProduct.hasNext()).isTrue();
        assertThat(cartesianProduct.next()).containsExactly(1, 4);
        // No more cartesian product
        cartesianProduct.next();
    }
}
