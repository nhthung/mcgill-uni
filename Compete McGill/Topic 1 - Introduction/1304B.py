import sys

def solve(n,m,strings):
    pass

lines=sys.stdin.readlines()
n,m=lines[0].split()
strings=[*map(str.strip,lines[1:])]
solve(n,m,strings)