/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This generator generates simple combinations from specified multiple lists.
 * Set of lists is specified in the constructor of generator, while length depends
 * on the size of the set of lists.
 * <p>
 * A simple k-combination of a finite sets S(1), S(2)...S(k) is a set of k elements
 * taken one each from sets S(1), S(2)..S(k).
 * <p>
 * Example. Generate 3-combination from the lists (1, 2, 3), (4, 5, 6), (7, 8, 9).
 * <p>
 * <blockquote>
 * 
 * <pre>
 * 
 * Generator.combinationFromList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9))
 *                  .stream()
 *                  .collect(Collectors.&lt;List&lt;Integer&gt;&gt;toList())
 * 
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Julius Iglesia
 * @version 3.0
 * @see SimpleCombinationFromListIterator
 * @param <T>
 *            Type of elements in the combination
 */
class SimpleCombinationFromListGenerator<T> implements IGenerator<List<T>> {

    final List<List<T>> originalVector;

    /**
     * Constructor
     * 
     * @param vector
     *            Vector which is used for generating the combination
     */
    public SimpleCombinationFromListGenerator(Collection<List<T>> vector) {
        this.validateParameters(vector);
        this.originalVector = new ArrayList<>(vector);
    }

    /**
     * Creates an iterator of the simple combinations
     */
    @Override
    public Iterator<List<T>> iterator() {
        return new SimpleCombinationFromListIterator<T>(this);
    }

    @Override
    public Stream<List<T>> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }
    
    private void validateParameters(Collection<List<T>> vector) {
        for(List<T> v: vector) {
            if (v.size() <= 0) {
                throw new RuntimeException("A list must not be not empty.");
            }
        }
    }
}
