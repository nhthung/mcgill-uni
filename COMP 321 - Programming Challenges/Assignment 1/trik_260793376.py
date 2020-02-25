#!/usr/bin/python3

import sys

def trik(moves):
    # 3 cups, 1 means has ball, 0 means no ball
    cups = [1,0,0]

    # Execute each move by swapping cups
    for move in moves:
        if move == 'A':
            cups[0], cups[1] = cups[1], cups[0]

        elif move == 'B':
            cups[1], cups[2] = cups[2], cups[1]

        else: # move == 'C':
            cups[0], cups[2] = cups[2], cups[0]
    
    # Find index of cup with ball, return that index + 1
    for i in range(len(cups)):
        if cups[i] == 1:
            return i + 1

for line in sys.stdin:
    print(trik(line.strip()))