[![Build Status](https://secure.travis-ci.org/dpaukov/combinatoricslib3.svg)](http://travis-ci.org/dpaukov/combinatoricslib3) 
[![Coverage Status](https://coveralls.io/repos/github/dpaukov/combinatoricslib3/badge.svg?branch=master)](https://coveralls.io/github/dpaukov/combinatoricslib3?branch=master)

```
     ___                _     _             _             _             __ _ _       _____ 
    / __\___  _ __ ___ | |__ (_)_ __   __ _| |_ ___  _ __(_) ___ ___   / /(_) |__   |___ / 
   / /  / _ \| '_ ` _ \| '_ \| | '_ \ / _` | __/ _ \| '__| |/ __/ __| / / | | '_ \    |_ \ 
  / /__| (_) | | | | | | |_) | | | | | (_| | || (_) | |  | | (__\__ \/ /__| | |_) |  ___) |
  \____/\___/|_| |_| |_|_.__/|_|_| |_|\__,_|\__\___/|_|  |_|\___|___/\____/_|_.__/  |____/ 
```  

New version of the combinatorics library for java

1. [Simple combinations](#1-simple-combinations)
2. [Combinations with repetitions](#2-combinations-with-repetitions)
3. [Simple permutations](#3-simple-permutations)

###1. Simple combinations
A simple k-combination of a finite set S is a subset of k distinct elements of S. Specifying a subset does not arrange them in a particular order. As an example, a poker hand can be described as a 5-combination of cards from a 52-card deck: the 5 cards of the hand are all distinct, and the order of the cards in the hand does not matter.

Example. Generate 3-combination of the set (red, black, white, green, blue).

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
A k-multicombination or k-combination with repetition of a finite set S is given by a sequence of k not necessarily distinct elements of S, where order is not taken into account.

As an example. Suppose there are 2 types of fruits (apple and orange) at a grocery store, and you want to buy 3 pieces of fruit. You could select
- (apple, apple, apple)
- (apple, apple, orange)
- (apple, orange, orange)
- (orange, orange, orange)

Example. Generate 3-combinations with repetitions of the set (apple, orange). You can pass an array as the parameter in the function.
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
A permutation is an ordering of a set in the context of all possible orderings. For example, the set containing the first three digits, 123, has six permutations: 123, 132, 213, 231, 312, and 321.

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

This generator can produce the permutations *even if the initial vector has duplicates*. For example, all permutations of (1, 1, 2, 2):

```java
   Generator.permutation(1, 1, 2, 2)
       .simple()
       .stream()
       .forEach(System.out::println);
```

The result of all possible permutations
```
   [1, 1, 2, 2]
   [1, 2, 1, 2]
   [1, 2, 2, 1]
   [2, 1, 1, 2]
   [2, 1, 2, 1]
   [2, 2, 1, 1]
```
