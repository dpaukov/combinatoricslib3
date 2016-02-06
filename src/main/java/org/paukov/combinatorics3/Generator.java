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

}
