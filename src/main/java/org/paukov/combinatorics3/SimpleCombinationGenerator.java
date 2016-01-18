/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This generator generates simple combinations from specified core set by
 * specified length. Core set and length are specified in the constructor of
 * generator
 * <p>
 * A simple k-combination of a finite set S is a subset of k distinct elements
 * of S. Specifying a subset does not arrange them in a particular order. As an
 * example, a poker hand can be described as a 5-combination of cards from a
 * 52-card deck: the 5 cards of the hand are all distinct, and the order of the
 * cards in the hand does not matter.
 * <p>
 * Example. Generate 3-combination of the set (red, black, white, green, blue).
 * <p>
 * <blockquote>
 * 
 * <pre>
 * 
 * // Create the initial vector
 * ICombinatoricsVector&lt;String&gt; initialVector = Factory.createVector(new String[] {
 * 		&quot;red&quot;, &quot;black&quot;, &quot;white&quot;, &quot;green&quot;, &quot;blue&quot; });
 * 
 * // Create a simple combination generator to generate 3-combinations of the
 * // initial vector
 * Generator&lt;String&gt; gen = Factory.createSimpleCombinationGenerator(initialVector,
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
 * @see SimpleCombinationIterator
 * @param <T>
 *            Type of elements in the combination
 */
class SimpleCombinationGenerator<T> implements IGenerator<List<T>> {

	final List<T> originalVector;
	final int combinationLength;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original vector which is used for generating the combination
	 * @param combinationsLength
	 *            Length of the combinations
	 */
    SimpleCombinationGenerator(List<T> originalVector,
			int combinationsLength) {
		this.originalVector = new ArrayList<T>(originalVector);
		this.combinationLength = combinationsLength;
	}



    /**
	 * Creates an iterator of the simple combinations (without repetitions)
	 */
	@Override
	public Iterator<List<T>> iterator() {
		return new SimpleCombinationIterator<T>(this);
	}

    @Override
    public Stream<List<T>> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }


}
