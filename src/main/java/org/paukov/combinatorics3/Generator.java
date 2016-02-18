/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import java.util.Arrays;
import java.util.Collection;


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

}
