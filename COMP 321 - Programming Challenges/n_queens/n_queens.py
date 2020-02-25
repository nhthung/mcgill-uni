'''
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.
'''
def n_queens(n: int) -> int:
    count = [0]
    col, diag1, diag2 = [0]*n, [0]*(2*n-1), [0]*(2*n-1)
    
    def _n_queens(r):
        if r == n:
            count[0] += 1
            return
        for c in range(n):
            if col[c] or diag1[c+r] or diag2[c+n-1-r]:
                continue
                
            col[c] = diag1[c+r] = diag2[c+n-1-r] = 1
            _n_queens(r + 1)
            col[c] = diag1[c+r] = diag2[c+n-1-r] = 0

    _n_queens(0)
    return count[0]

'''
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
'''
def n_queens_sols(n: int):
    sols = []
    
    cols = [0]*n
    diag1 = [0]*(2*n-1)
    diag2 = [0]*(2*n-1)

    def fmt_sol(sol):
        for i in range(len(sol)):
            sol[i] = '.'*sol[i] + 'Q' + '.'*(n - sol[i] - 1)
    
    def _n_queens_sols(row, sol):
        if row == n:
            fmt_sol(sol)
            sols.append(sol)
            return
        for col in range(n):
            if cols[col] or diag1[col+row] or diag2[col+n-1-row]:
                continue
            
            cols[col] = diag1[col+row] = diag2[col+n-1-row] = 1
            _n_queens_sols(row+1, sol + [col])
            cols[col] = diag1[col+row] = diag2[col+n-1-row] = 0
    
    _n_queens_sols(0, [])
    return sols

print(n_queens(4))
print(n_queens_sols(4))

def n_queens_jovi(n):
    board = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if board[i][j] == 0:
                board[i][j] = 1
            for cur_i in range(n):
                if cur_i == i:
                    continue
                board[cur_i][j] = 2
            for cur_j in range(n):
                for cur_j == j:
                    continue
                board[i][cur_j] = 2