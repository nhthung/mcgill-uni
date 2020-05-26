'''
You are on an n×m grid where each square on the grid has a digit on it. From a given square that has digit k on it, a Move consists of jumping exactly k squares in one of the four cardinal directions. A move cannot go beyond the edges of the grid; it does not wrap. What is the minimum number of moves required to get from the top-left corner to the bottom-right corner?

Input
Each input will consist of a single test case. Note that your program may be run multiple times on different inputs. The first line of input contains two space-separated integers n and m (1≤n,m≤500), indicating the size of the grid. It is guaranteed that at least one of n and m is greater than 1.

The next n lines will each consist of m digits, with no spaces, indicating the n×m grid. Each digit is between 0 and 9, inclusive.

The top-left corner of the grid will be the square corresponding to the first character in the first line of the test case. The bottom-right corner of the grid will be the square corresponding to the last character in the last line of the test case.

Output
Output a single integer on a line by itself representing the minimum number of moves required to get from the top-left corner of the grid to the bottom-right. If it isn’t possible, output -1.

Link: https://open.kattis.com/problems/grid
'''
import sys

def grid(g):
    '''
    Given n x m grid g, e.g.
        ['0123'
         '1234']
    Return minimum number of steps needed to reach
    bottom-right cell g[n-1][m-1]
    '''
    n, m = len(g), len(g[0])

    # DP table: steps[i][j] = minimum number of steps to cell ij
    steps = [[-1]*m for _ in range(n)]
    steps[0][0] = 0

    # Hashmap/dictionary {row: [col1, col2, ...] storing visited cells
    visited = {}

    # Function to check if cell ij is visited
    def is_visited(i, j):
        return steps[i][j] > -1

    # Function returning generator of valid moves from cell ij
    def moves(i, j):
        k = int(g[i][j])
        
        # Move is valid if within bounds and reaches new cell
        for r, c in ((i, j+k), (i+k, j), (i, j-k), (i-k, j)):
            if 0 <= r < n and 0 <= c < m and not is_visited(r, c):
                yield r, c

    # Queue storing moves
    moves_q = [(0, 0)]

    # Make all valid moves
    while len(moves_q) > 0:
        # Get valid moves from current cell ij
        i, j = moves_q.pop(0)

        # Make valid moves
        for r, c in moves(i, j):
            # Enqueue valid move
            moves_q.append((r, c))

            # Increment step count
            steps[r][c] = steps[i][j] + 1

    # Return minimum number of steps or -1 if couldn't reach bottom-right cell
    return steps[n-1][m-1]

# Extract grid from input
g = [* map(str.strip, sys.stdin)][1:]
print(grid(g))