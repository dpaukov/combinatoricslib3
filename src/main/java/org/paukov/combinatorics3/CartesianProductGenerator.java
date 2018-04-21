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
 * This generator generates Cartesian product from specified multiple lists.
 * Set of lists is specified in the constructor of generator to generate k-element Cartesian product,
 * where k is the size of the set of lists.
 * <p>
 * A simple k-element Cartesian product of a finite sets S(1), S(2)...S(k) is a set 
 * of all ordered pairs (x(1), x(2)...x(k), where x(1) ∈ S(1), x(2) ∈ S(2) ... x(k) ∈ S(k)
 * <p>
 * Example. Generate 3-element Cartesian product from (1, 2, 3), (4, 5, 6), (7, 8, 9).
 * <p>
 * <blockquote>
 * 
 * <pre>
 * 
 * Generator.cartesianProduct(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9))
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
 * @see CartesianProductIterator
 * @param <T>
 *            Type of elements in the Cartesian product
 */
class CartesianProductGenerator<T> implements IGenerator<List<T>> {

    final List<List<T>> originalVector;

    /**
     * Constructor
     * 
     * @param vector
     *            Vector which is used for generating the Cartesian product
     */
    public CartesianProductGenerator(Collection<List<T>> vector) {
        this.originalVector = new ArrayList<>(vector);
    }

    /**
     * Creates an iterator of the cartesian product
     */
    @Override
    public Iterator<List<T>> iterator() {
        return new CartesianProductIterator<T>(this);
    }

    @Override
    public Stream<List<T>> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }
}
