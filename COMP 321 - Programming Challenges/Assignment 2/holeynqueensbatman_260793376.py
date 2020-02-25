import sys

'''
Kattis: https://open.kattis.com/problems/holeynqueensbatman

The N-queens problem is the problem of placing N queens on a N×N chessboard
so that no queen shares a row, column or a diagonal with any other queen.
Essentially, we are trying to place the queens without any queen threatening another.
For example, the first image below (without holes in the board) is a solution to the 8-queens problem.

For this problem, consider the problem we’ll call the ‘holey N-queens problem’.
Instead of having an everyday chessboard (of arbitrary size), your chessboard is
like the second image above (without queens): it has holes through some of the squares.
You can’t place a queen on a square that has a hole, but a queen can threaten another
even if there is a hole on the path between them. Given a holey chessboard,
you must find placements for the N queens so that no queen threatens another.
The third image above (with holes and queens) shows one such solution.
'''
def holeynqueens(n, holes):
    '''
    Count n-queen solutions

    Parameters:
        n    : board size (n x n) and number of queens to place
        holes: coordinates of holes as list of (row, column) pairs
    '''
    # Counter for solutions, as list to pass by reference
    # because ints can only be passed as value
    count = [0]

    # Represent board as 3 1-D arrays: columns, 2 diagonals
    col, diag1, diag2 = [0]*n, [0]*(2*n-1), [0]*(2*n-1)

    # Represent hole coordinates as hashmap (dictionary)
    # instead of list of pairs
    holes = to_dict_holes(holes)
    
    # Inner function as recurrence step of pruned search
    def _n_queens(r):
        if r == n:
            count[0] += 1
            return

        # Pruned search
        for c in range(n):
            # Invalid cell: can be attacked by other queen(s) or on hole,
            # skip (`continue`) to next column
            if col[c] or diag1[c+r] or diag2[c+n-1-r] \
                    or (r in holes and c in holes[r]): # On hole
                continue
                
            # Valid cell: place queen then move to next row continue search
            col[c] = diag1[c+r] = diag2[c+n-1-r] = 1
            _n_queens(r + 1)

            # Remove queen from cell, move to next column and continue search on same row
            col[c] = diag1[c+r] = diag2[c+n-1-r] = 0

    # Start search at first row (top of the board)
    _n_queens(0)

    # Return solution counter
    return count[0]

def to_dict_holes(holes):
    '''
    Convert hole coordinates from list to hashmap,
    with row as key and columns as values
    '''
    _holes = {}
    for r, c in holes:
        if r in _holes:
            _holes[r].append(c)
        else:
            _holes[r] = [c]
    return _holes


# Convert input to list
inp = [[*map(int, line.strip().split())] for line in sys.stdin]

i = 0
while i < len(inp):
    n, m = inp[i]
    if n == m == 0:
        break

    if m == 0:
        # No holes: empty list as coordinates
        print(holeynqueens(n, []))
        i += 1
    else:
        # Has holes: list of (row, column) pairs as coordinates
        print(holeynqueens(n, [inp[j] for j in range(i+1, i+1+m)]))
        i = i + 1 + m