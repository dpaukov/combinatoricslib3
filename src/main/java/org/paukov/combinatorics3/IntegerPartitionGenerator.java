/**
 * Combinatorics Library 3
 * Copyright 2009-2016 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics3;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class IntegerPartitionGenerator implements IGenerator<List<Integer>> {

    final Integer value;

    IntegerPartitionGenerator(Integer value) {
        this.value = value;
    }

    @Override
    public Iterator<List<Integer>> iterator() {
        return new IntegerPartitionIterator(this);
    }

    @Override
    public Stream<List<Integer>> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }
}
