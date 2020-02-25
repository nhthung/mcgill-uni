import sys

def holeynqueens(n, holes):
    count = [0]
    col, diag1, diag2 = [0]*n, [0]*(2*n-1), [0]*(2*n-1)

    holesd = {}
    for r, c in holes:
        if r in holesd:
            holesd[r].append(c)
        else:
            holesd[r] = [c]
    
    def _n_queens(r):
        if r == n:
            count[0] += 1
            return
        for c in range(n):
            if col[c] or diag1[c+r] or diag2[c+n-1-r] or (r in holesd and c in holesd[r]):
                continue
                
            col[c] = diag1[c+r] = diag2[c+n-1-r] = 1
            _n_queens(r + 1)
            col[c] = diag1[c+r] = diag2[c+n-1-r] = 0

    _n_queens(0)
    return count[0]

inp = [[*map(int, line.strip().split())] for line in sys.stdin]

i = 0
while i < len(inp):
    n, m = inp[i]
    if n == m == 0:
        break

    if m == 0:
        print(holeynqueens(n, []))
        i += 1
    else:
        print(holeynqueens(n, [inp[j] for j in range(i+1, i+1+m)]))
        i = i + 1 + m