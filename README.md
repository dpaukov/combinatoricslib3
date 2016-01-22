[![Build Status](https://secure.travis-ci.org/dpaukov/combinatoricslib3.png)](http://travis-ci.org/dpaukov/combinatoricslib3) [![Coverage Status](https://coveralls.io/repos/github/dpaukov/combinatoricslib3/badge.svg?branch=master)](https://coveralls.io/github/dpaukov/combinatoricslib3?branch=master)

combinatoricslib 3.0
====================

New version of the combinatorics library

###1. Simple combinations
A simple k-combination of a finite set S is a subset of k distinct elements of S. Specifying a subset does not arrange them in a particular order. As an example, a poker hand can be described as a 5-combination of cards from a 52-card deck: the 5 cards of the hand are all distinct, and the order of the cards in the hand does not matter.

Example. Generate 3-combination of the set (red, black, white, green, blue).

```java
   Generator.combination("red", "black", "white", "green", "blue")
       .simple(3)
       .stream()
       .forEach(System.out::println);

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
