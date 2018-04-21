/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class Generator {

    public static <T> CombinationGenerator<T> combination(T... args) {
        return new CombinationGenerator<>(Arrays.asList(args));
    }

    public static <T> CombinationGenerator<T> combination(Collection<T> collection) {
        return new CombinationGenerator<>(collection);
    }

    public static <T> PermutationGenerator<T> permutation(T... args) {
        return new PermutationGenerator<>(Arrays.asList(args));
    }

    public static <T> PermutationGenerator<T> permutation(Collection<T> collection) {
        return new PermutationGenerator<>(collection);
    }

    public static <T> SubSetGenerator<T> subset(Collection<T> collection) {
        return new SubSetGenerator<>(collection);
    }

    public static <T> SubSetGenerator<T> subset(T... args) {
        return new SubSetGenerator<>(Arrays.asList(args));
    }

    public static IGenerator<List<Integer>> partition(Integer value) {
        return new IntegerPartitionGenerator(value);
    }
    
    public static <T> IGenerator<List<T>> cartesianProduct(List<T>... args) {
        return new CartesianProductGenerator<>(Arrays.asList(args));
    }
}
