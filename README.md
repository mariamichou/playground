# Playground  

The purpose of this repository is to have a common space in order to practice `Springboot`, `Java`, `Groovy` and `Spock`.
Java version is `1.8`

## Colours
This package contains an Enum class, `Colour`, which contains rainbow colours.
The purpose of `Colour` is to represent a basic list of values, indexes, i.e. with a default order.  
In addition to this list, if we're given another random, dynamic list of values, we will show that we can render the 
basic list on top, ordered by index ascending, following by the random list, sorted by name and also assigning an index 
(_idempotent_, guaranteed to be the same every time for the same value, case insensitive).

E.g. for the list of rainbow colours: `[RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET]` with indexes: `[0, ..., 6]`, 
if we're given the following list: `[PURPLE, PINK, BLACK]`, then the final list will be:  
`[RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET, BLACK, PINK, PURPLE]` with indexes: `[0, ..., 7, 8, 9]`

The last random colours are sorted by name in ascending order and given an increasing index, starting from the last 
index of the last rainbow colour. The next time another list is given, the non-rainbow colours will be re-arranged in 
ascending order, just like the aforementioned example.

This example can be a use case for a hardware store where we sell some popular paint colours and less popular ones or 
ones in high stock.

Another use case scenario can be applied on a medical website listing popular/basic diseases always on top with a fixed 
order always, while i.e. every month populating some other, less popular, diseases at the bottom of the page. 
For that, we would need to have a sorting mechanism (for us, it's enough to sort by name, but this can easily change, 
depending on the second parameter - comparing function - in the `toSorted` method).

Package `colours` contains:
- `Colour` class is the enum with basic methods for ordering by name or index.
- `Utils` class contains helper methods.
- `ColourTest` class has unit tests written in `Groovy` using `Spock` framework, a very powerful tool for data driven 
tests (parameterized tests).