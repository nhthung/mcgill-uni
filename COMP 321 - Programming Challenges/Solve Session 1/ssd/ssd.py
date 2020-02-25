#!/usr/bin/python3

import sys

inp = [*sys.stdin]

def get_ssd(idx, b, n):
    ssd = 0
    p = 1
    while n > 0:
        ssd += ((n % b**p) / b**(p-1))**2
        n -= n % b**p
        p += 1
    return ' '.join((str(idx), str(int(ssd))))

if int(inp[0]) > 0:
    for i in range(1, 1+int(inp[0])):
        line = inp[i].split()
        print(get_ssd(int(line[0]), int(line[1]), int(line[2])))