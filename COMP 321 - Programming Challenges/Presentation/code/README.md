## Methods
### Brute force:
* adding all choices of a, b, c into 3 lists, no duplicates
* try all combinations from 3 lists
* correct solution
* kattis result: TLE at test 9/15
* reason: too slow, O(n^3)

### Brute force 2:
* adding all choices of a, b into 2 lists, no duplicates
* try all (a, b) combos from the 2 lists, take c = 10000 - a - b
* correct solution
* kattis result: TLE at test 12/15
* reason: too slow, O(n^2)