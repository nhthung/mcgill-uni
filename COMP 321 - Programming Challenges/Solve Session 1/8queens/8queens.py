import sys

def eight_queens(board):
    n = len(board)
    cols = [0] * n
    diag1 = [0] * (2 * n - 1)
    diag2 = [0] * (2 * n - 1)

    for row in range(n):
        col = board[row].find('*')
        
        if col == -1 or cols[col] or diag1[col + row] or diag2[col + n - row - 1]:
            return 'invalid'

        cols[col] = diag1[col + row] = diag2[col + n - row - 1] = 1
    
    return 'valid'

board = list(sys.stdin)
print(eight_queens(board))