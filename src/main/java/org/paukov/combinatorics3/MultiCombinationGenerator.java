/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;


import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This generator generates multi-combinations (with repetitions) from specified
 * core set by specified length. Core set and length are specified in the
 * constructor of generator
 * <p>
 * A k-multicombination or k-combination with repetition of a finite set S is
 * given by a sequence of k not necessarily distinct elements of S, where order
 * is not taken into account.
 * <p>
 * As an example. Suppose there are 2 types of fruits (apple and orange) at a
 * grocery store, and you want to buy 3 pieces of fruit. You could select
 * <ul>
 * <li>(apple, apple, apple)
 * <li>(apple, apple, orange)
 * <li>(apple, orange, orange)
 * <li>(orange, orange, orange)
 * </ul>
 * <p>
 * Generate 3-combinations with repetitions of the set (apple, orange).
 * <p>
 * <p>
 * <blockquote>
 * 
 * <pre>
 * 
 * // Create the initial vector of (apple, orange)
 * ICombinatoricsVector&lt;String&gt; initialVector = Factory.createVector(new String[] {
 * 		&quot;apple&quot;, &quot;orange&quot; });
 * 
 * // Create a multi-combination generator to generate 3-combinations of
 * // the initial vector
 * Generator&lt;String&gt; gen = Factory.createMultiCombinationGenerator(initialVector,
 * 		3);
 * 
 * // Print all possible combinations
 * for (ICombinatoricsVector&lt;String&gt; combination : gen) {
 * 	System.out.println(combination);
 * }
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro Paukov
 * @version 3.0
 * @see MultiCombinationIterator
 * @param <T>
 *            Type of elements in the combination
 */
class MultiCombinationGenerator<T> implements IGenerator<List<T>> {

    final List<T> originalVector;
    final int combinationLength;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original/initial vector which is used for combination
	 *            generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 */
	 MultiCombinationGenerator(Collection<T> originalVector,
			int combinationsLength) {
		this.originalVector = new ArrayList<>(originalVector);
		if (combinationsLength < 0)
			combinationLength = 0;
		else
			combinationLength = combinationsLength;
	}


	/**
	 * Creates iterator of combinations with repetitions
	 */
	@Override
	public Iterator<List<T>> iterator() {
		return new MultiCombinationIterator<T>(this);
	}


    @Override
    public Stream<List<T>> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }
}
