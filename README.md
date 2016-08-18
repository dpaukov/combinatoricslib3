[![Build Status](https://secure.travis-ci.org/dpaukov/combinatoricslib3.svg)](http://travis-ci.org/dpaukov/combinatoricslib3) 
[![Coverage Status](https://coveralls.io/repos/github/dpaukov/combinatoricslib3/badge.svg?branch=master)](https://coveralls.io/github/dpaukov/combinatoricslib3?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.dpaukov/combinatoricslib3.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.dpaukov%22%20AND%20a%3A%22combinatoricslib3%22)

```
     ___                _     _             _             _             __ _ _       _____ 
    / __\___  _ __ ___ | |__ (_)_ __   __ _| |_ ___  _ __(_) ___ ___   / /(_) |__   |___ / 
   / /  / _ \| '_ ` _ \| '_ \| | '_ \ / _` | __/ _ \| '__| |/ __/ __| / / | | '_ \    |_ \ 
  / /__| (_) | | | | | | |_) | | | | | (_| | || (_) | |  | | (__\__ \/ /__| | |_) |  ___) |
  \____/\___/|_| |_| |_|_.__/|_|_| |_|\__,_|\__\___/|_|  |_|\___|___/\____/_|_.__/  |____/ 
```  

## Version 3.1.0

New implementation of the combinatorics library for Java 8. The previous versions of the library can 
be found [here](https://github.com/dpaukov/combinatoricslib)

1. [Simple combinations](#1-simple-combinations)
2. [Combinations with repetitions](#2-combinations-with-repetitions)
3. [Simple permutations](#3-simple-permutations)
4. [Permutations with repetitions](#4-permutations-with-repetitions)
5. [Subsets](#5-subsets)


| Description                      | Is Order Important? | Is Repetition Allowed? | Stream  |
|----------------------------------|:-------------------:|:----------------------:|---------| 
| [Simple combinations](#1-simple-combinations) | No | No | `Generator.combination(...).simple(n).stream()` |
| [Combinations with repetitions](#2-combinations-with-repetitions) | No | Yes | `Generator.combination(...).multi(n).stream()` |
| [Simple permutations](#3-simple-permutations) | Yes | No | `Generator.permutation(...).simple().stream()` |
| [Permutations with repetitions](#4-permutations-with-repetitions) | Yes | Yes | `Generator.permutation(...).withRepetitions(n).stream()` |


###1. Simple combinations
A simple k-combination of a finite set S is a subset of k distinct elements of S. 
Specifying a subset does not arrange them in a particular order. As an example, a poker hand can 
be described as a 5-combination of cards from a 52-card deck: the 5 cards of the hand are all distinct, 
and the order of the cards in the hand does not matter.

Let's generate all 3-combination of the set of 5 colors (red, black, white, green, blue).

```java
   List<List<String>> combinations = Generator.combination("red", "black", "white", "green", "blue")
       .simple(3)
       .stream()
       .collect(Collectors.<List<String>>toList());
   combinations.stream().forEach(System.out::println);

```
And the result of 10 combinations
```
   [red, black, white]
   [red, black, green]
   [red, black, blue]
   [red, white, green]
   [red, white, blue]
   [red, green, blue]
   [black, white, green]
   [black, white, blue]
   [black, green, blue]
   [white, green, blue]
```

###2. Combinations with repetitions
A k-multicombination or k-combination with repetition of a finite set S is given by a sequence of 
k not necessarily distinct elements of S, where order is not taken into account.

As an example. Suppose there are 2 types of fruits (apple and orange) at a grocery store, and you 
want to buy 3 pieces of fruit. You could select
- (apple, apple, apple)
- (apple, apple, orange)
- (apple, orange, orange)
- (orange, orange, orange)

Example. Generate 3-combinations with repetitions of the set (apple, orange). You can pass an array 
as the parameter in the function.
```java
   Generator.combination(new String[] { "apple", "orange" })
       .multi(3)
       .stream()
       .forEach(System.out::println);
```
And the result of 4 multi-combinations
```
   [apple, apple, apple]
   [apple, apple, orange]
   [apple, orange, orange]
   [orange, orange, orange]
```

###3. Simple permutations
A permutation is an ordering of a set in the context of all possible orderings. For example, the set 
containing the first three digits, 123, has six permutations: 123, 132, 213, 231, 312, and 321.

This is an example of the permutations of the 3 string items (apple, orange, cherry):

```java
   Generator.permutation("apple", "orange", "cherry")
       .simple()
       .stream()
       .forEach(System.out::println);
```

The result of 6 permutations
```
   [apple, orange, cherry]
   [apple, cherry, orange]
   [cherry, apple, orange]
   [cherry, orange, apple]
   [orange, cherry, apple]
   [orange, apple, cherry]
```

This generator can produce the permutations *even if an initial vector has duplicates*. For example, 
all permutations of (1, 1, 2, 2):

```java
   Generator.permutation(1, 1, 2, 2)
       .simple()
       .stream()
       .forEach(System.out::println);
```

The result does not have duplicates. All permutations are distinct by default.
```
   [1, 1, 2, 2]
   [1, 2, 1, 2]
   [1, 2, 2, 1]
   [2, 1, 1, 2]
   [2, 1, 2, 1]
   [2, 2, 1, 1]
```
Notice that we have 6 permutations here instead of 24. If you still need all permutations, 
you should call method `simple(PermutationGenerator.TreatDuplicatesAs.IDENTICAL)`.

###4. Permutations with repetitions
Permutation may have more elements than slots. For example, all possible permutation of '12' 
in three slots are: 111, 211, 121, 221, 112, 212, 122, and 222.

Let's generate all possible permutations with repetitions of 3 elements from the set of apple and orange.

```java
   List<List<String>> permutations = Generator
        .permutation("apple", "orange")
        .withRepetitions(3)
        .stream()
        .collect(Collectors.<List<String>>toList());
        
   permutations.stream().forEach(System.out::println);
```

And the result of 8 permutations

```
   [apple, apple, apple]
   [orange, apple, apple]
   [apple, orange, apple]
   [orange, orange, apple]
   [apple, apple, orange]
   [orange, apple, orange]
   [apple, orange, orange]
   [orange, orange, orange]
```

###5. Subsets
A set A is a subset of a set B if A is "contained" inside B. A and B may coincide. 
The relationship of one set being a subset of another is called inclusion or sometimes containment.

Examples:

The set (1, 2) is a proper subset of (1, 2, 3).
Any set is a subset of itself, but not a proper subset.
The empty set, denoted by ∅, is also a subset of any given set X.
All subsets of (1, 2, 3) are:

- ()
- (1)
- (2)
- (1, 2)
- (3)
- (1, 3)
- (2, 3)
- (1, 2, 3)

Here is a piece of code that generates all possible subsets of (one, two, three)

```java
   List<List<String>> subsets = Generator
        .subset("one", "two", "three")
        .simple()
        .stream()
        .collect(Collectors.<List<String>>toList());
   subsets.stream().forEach(System.out::println);

```
And the result of all possible 8 subsets
```
   []
   [one]
   [two]
   [one, two]
   [three]
   [one, three]
   [two, three]
   [one, two, three]
```

### The latest release

The latest release of the library is available through The Maven Central Repository [here](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.dpaukov%22%20AND%20a%3A%22combinatoricslib3%22)
Include the following section into your `pom.xml` file.

```xml
<dependency>
    <groupId>com.github.dpaukov</groupId>
    <artifactId>combinatoricslib3</artifactId>
    <version>3.0.0</version>
</dependency>
```