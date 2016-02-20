/**
 * Combinatorics Library 3
 * Copyright 2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This generator generates all possible permutations of the specified initial
 * vector
 * <p>
 * A permutation is an ordering of a set in the context of all possible
 * orderings. For example, the set containing the first three digits, 123, has
 * six permutations: 123, 132, 213, 231, 312, and 321.
 * <p>
 * This is an example of the permutations of 3 string items (apple, orange,
 * cherry):
 * <p>
 * <blockquote>
 *
 * <pre>
 *
 * // Create the initial vector of 3 elements (apple, orange, cherry)
 * ICombinatoricsVector&lt;String&gt; originalVector = Factory
 * 		.createVector(new String[] { &quot;apple&quot;, &quot;orange&quot;, &quot;cherry&quot; });
 *
 * // Create the permutation generator by calling the appropriate method in the
 * // Factory class
 * Generator&lt;String&gt; gen = Factory.createPermutationGenerator(originalVector);
 *
 * // Print the result
 * for (ICombinatoricsVector&lt;String&gt; perm : gen)
 * 	System.out.println(perm);
 *
 * </pre>
 *
 * </blockquote>
 * <p>
 * And the result
 * <p>
 * <blockquote>
 *
 * <pre>
 *   CombinatoricsVector=([apple, orange, cherry], size=3)
 *   CombinatoricsVector=([apple, cherry, orange], size=3)
 *   CombinatoricsVector=([cherry, apple, orange], size=3)
 *   CombinatoricsVector=([cherry, orange, apple], size=3)
 *   CombinatoricsVector=([orange, cherry, apple], size=3)
 *   CombinatoricsVector=([orange, apple, cherry], size=3)
 * </pre>
 *
 * </blockquote>
 * <p>
 *
 * @author Dmytro Paukov
 * @version 2.0
 * @param <T>
 *            Type of the elements in the permutations
 */
class SimplePermutationGenerator<T> implements IGenerator<List<T>> {

    final boolean _hasDuplicates;
    final boolean _treatAsIdentical;
    final List<T> _originalVector;

    /**
     * Constructor
     *
     * @param vector
     *            Vector which is used for permutation generation
     * @param treatAsIdentical
     *            True if the generator should treat the vector as identical
     */
    SimplePermutationGenerator(Collection<T> vector,
                               boolean treatAsIdentical) {
        _hasDuplicates = hasDuplicates(vector);
        _treatAsIdentical = treatAsIdentical;
        _originalVector = new ArrayList<>(vector);
    }



    /**
     * Creates an iterator
     *
     * @see org.paukov.combinatorics3.Generator#iterator()
     */
    @Override
    public Iterator<List<T>> iterator() {
        if (isDuplicateIterator())
            return new DuplicatedPermutationIterator<>(this);
        else
            return new SimplePermutationIterator<>(this);

    }


    @Override
    public Stream<List<T>> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }


    boolean isDuplicateIterator() {
        return (!_treatAsIdentical && _hasDuplicates);
    }


    static private  <T> boolean hasDuplicates(Collection<T> collection) {
        if (collection.size() <= 1) {
            return false;
        }
        Set<T> set = new HashSet<T>(collection);
        return set.size() < collection.size();
    }

}

