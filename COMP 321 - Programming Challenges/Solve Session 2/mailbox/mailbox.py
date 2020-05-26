'''
Input
The input starts with a single integer N (1≤N≤100) indicating the number of test cases to follow. Each test case is described by a line containing two integers: k and m, separated by a single space.

Output
For each test case print one line with a single integer indicating the minimum number of fire-crackers that is needed, in the worst case, in order to figure out how many crackers the mailbox prototype can withstand.

Link: https://open.kattis.com/problems/mailbox
Detailed solution: http://oeis.org/A280433

FORMULA	
From Jon E. Schoenfield, Jan 10 2017: (Start)

Let f(L,U,B) be the number of firecrackers needed to determine M (in the worst case, using an optimal strategy), given that M is known to be in the closed interval [L, U] and B mailboxes remain available for testing. Then if B=1, we must sequentially test each integer number k of firecrackers from L+1 upward until the mailbox fails (since a failure will destroy our only remaining mailbox), and in the worst case (largest total number of firecrackers used) we will use L+1 + L+2 + ... + U firecrackers, so

   f(L,U,1) = Sum_{k=L+1..U} k = (L+1+U)*(U-L)/2.

For B >= 2 and U > L, we have

   f(L,U,B) = min_{k=L+1..U} (k + max(f(k,U,B), f(L,k-1,B-1)))

(where the minimum gives the best strategy (minimizing the total number of firecrackers needed), and max(f(k,U,B), f(L,k-1,B-1)) is the worst-case result from the outcomes of mailbox success and failure at a k-firecracker test)

and when L=U, we have

   f(L,U,B) = 0 (since M has been determined).

Using the above recursive formula, we can compute

   a(n) = f(0,n,2). (End)

It looks as though lim_{n->inf} a(n)/n^(3/2) = 0.818... - Jon E. Schoenfield, Jan 12 2017

EXAMPLE	
For n = 3, the optimal strategy uses 2 firecrackers for the first test, and in the worst case, the mailbox holds up and we need to try 3 firecrackers, for a(3) = 2 + 3 = 5 in total.

For n = 6, the optimal strategy uses 3 firecrackers for the first test. In the worst case, the mailbox holds up and we try a second test with 5. Again, the worst case is that the mailbox withstands the test, so the third test uses 6, for a final sum of a(6) = 3 + 5 + 6 = 14.

PROG	
(Python 2)

seen = {}

def solve(start, end, boxes):
    tup = (start, end, boxes)
    if boxes == 1 or start >= end-1:
        val = (start + end) * (end-start+1) / 2
        seen[tup] = val
        return val

    lowest = 100000000000000000
    for x in range(end-1, start, -1):
        firstup = (x+1, end, boxes)
        first = seen[firstup] if firstup in seen else solve(x+1, end, boxes)
        if first >= lowest:
            break

        secondtup = (start, x-1, boxes-1)
        second = seen[secondtup] if secondtup in seen else solve(start, x-1, boxes-1)

        if second >= lowest:
            break

        lowest = min(lowest, x + max(first, second))

    seen[tup] = lowest
    return lowest
'''
import sys

# 1-D DP memoization table
# Indexed by concatenating lower bound, upper bound of firecrackers, and k (number of mailboxes)
# Number of firecrackers m: 0 < m <= 100
# Number of mailboxes k: 0 < k <= 10
# => 10010010 max index
# => DP array of length 10010010 + 1 = 10010011
seen = [-1]*(10010011)

def mailbox(k, low, up):
    '''
    k: number of mailboxes
    low: known number of firecrackers a mailbox can withstand
    up: up + 1 = number of firecrackers that would blow up mailbox
    '''
    idx = low*100_000 + up*100 + k

    if k == 1 or low >= up-1:
        val = (low + up) * (up-low+1) / 2
        seen[idx] = int(val)
        return seen[idx]

    lowest = float('inf')

    for x in range(up-1, low, -1):
        first_idx = (x+1)*100_000 + up*100 + k
        first = seen[first_idx] if seen[first_idx] > -1 else mailbox(k, x+1, up)

        if low >= lowest:
            break

        second_idx = low*100_000 + (x-1)*100 + k-1
        second = seen[second_idx] if seen[second_idx] > -1 else mailbox(k-1, low, x-1)

        if second >= lowest:
            break

        lowest = min(lowest, x + max(first, second))

    seen[idx] = int(lowest)
    return seen[idx]

inp = map(lambda x: map(int, x.split()), sys.stdin)
for _inp in inp:
    _inp = [*_inp]
    if len(_inp) == 2:
        k, m = _inp[0], _inp[1]
        print(mailbox(k, low=0, up=m))