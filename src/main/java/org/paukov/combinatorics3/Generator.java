package org.paukov.combinatorics3;


import java.util.Arrays;


public class Generator {

    public static <T> CombinationGenerator<T> combination(T... args) {
        return new CombinationGenerator<>(Arrays.asList(args));
    }

}
