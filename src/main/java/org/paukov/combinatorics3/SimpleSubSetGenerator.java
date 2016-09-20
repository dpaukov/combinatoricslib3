/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


class SimpleSubSetGenerator<T> implements IGenerator<List<T>> {

    final List<T> originalVector;

    SimpleSubSetGenerator(Collection<T> originalVector) {
        this.originalVector = new ArrayList<>(originalVector);
    }

    @Override
    public Iterator<List<T>> iterator() {
        return new SimpleSubSetIterator<>(this);
    }

    @Override
    public Stream<List<T>> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }
}
