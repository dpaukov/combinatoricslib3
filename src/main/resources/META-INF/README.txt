
             CombinatoricsLib - release notes
                   by Dmytro Paukov


CombinatoricsLib 3.0 (2014)
- Added a method for calculating factorials using BigDecimal (Issue 10)


-----------------------------------------------------------------
CombinatoricsLib 2.1 (June 2014)
- Fixed an issue with the Maven Central Repository (Issue 8) and
  incorrect name of the <artifactId>combinatoricsLib</artifactId>


-----------------------------------------------------------------
CombinatoricsLib 2.0 (October 2012)
- A new general interface ICombinatoricsVector<T> has been introduced.
  All code has been re-factored to use this interface.
- The permutation generator can produce the permutations even
  if the initial vector has duplicates.
  For example, library can generate all permutations of (1,1,2,2).
- Factory class has been introduced to create all vectors and generators.
- All generators implement the java interface Iterable,
  so they can be used in the "foreach" statement directly.
- Filters have been introduced. All generated vectors can be easily filtered.
- Representation of the combinatorics vectors as strings has been
  changed to format: "CombinatoricsVector=( [elements], size=value)".
- Added the "complex" combination generator called ComplexCombinationGenerator
  and iterator to generate combinations in relation with
  permutations (list partitions).
- Add several methods to detect if a vector contains duplicated elements.
- SubSetGenerator has been updated to support duplicates in the original
  vector which represents the original set. To do that a new iterator
  called "SubListIterator" has been introduced.
- Added a constructor to create a combinatorics vector from an array.
- Added a factory class to make the library more flexible.

-----------------------------------------------------------------
CombinatoricsLib 1.0 (December 2011)
 - Added combinations with repetitions
 - Added more unit tests


-----------------------------------------------------------------
 CombinatoricsLib 0.2 (July 2010)
 - Added compositions


-----------------------------------------------------------------
 CombinatoricsLib 0.1 (January 2010)
 - Permutations (with and without repetitions)
 - Partitions
 - Subsets
 - Simple combinations