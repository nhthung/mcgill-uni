import sys
from pprint import pprint

def nine_knights(board):
    knight_count = 0

    def moves(i, j):
        for r, c in ((i+1, j+2), (i+2, j+1), (i+2, j-1), (i+1, j-2),
                     (i-1, j-2), (i-2,j-1), (i-2,j+1), (i-1,j+2)):
            if 0 <= r < 5 and 0 <= c < 5:
                yield r, c
    
    for r in range(5):
        for c in range(5):
            if board[r][c] == 'k':
                knight_count += 1
                for i, j in moves(r, c):
                    if board[i][j] == 'k':
                        return 'invalid'

    if knight_count != 9:
        return 'invalid'
    return 'valid'

board = [*map(str.strip, sys.stdin)]
print(nine_knights(board))
