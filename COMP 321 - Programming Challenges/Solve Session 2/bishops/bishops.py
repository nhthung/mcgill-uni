'''
Sam’s chessboard has size N×N. A bishop can move to any distance in any of the four diagonal directions. A bishop threatens another bishop if it can move to the other bishop’s position. Your task is to compute the maximum number of bishops that can be placed on a chessboard in such a way that no two bishops threaten each other.

Input
The input file consists of several lines. The line number i contains a positive integer representing the size of the i-th chessboard. The size of each chessboard is at most 1000000.

Output
The output file should contain the same number of lines as the input file. The i-th line should contain one number – the maximum number of bishops that can be placed on i-th chessboard without threatening each other.

Link: https://open.kattis.com/problems/bishops
Explanation: https://www.geeksforgeeks.org/maximum-bishops-that-can-be-placed-on-nn-chessboard/
'''

import sys

def bishops(n):
    if n <= 1:
        return n
    return 2*n - 2

inp = map(lambda x: int(x.strip()), sys.stdin)
for n in inp:
    print(bishops(n))