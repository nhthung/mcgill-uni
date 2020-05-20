
import sys

class Node:
    def __init__(self, val, parent=None):
        self.val = val
        self.parent = parent

def kitten(inp):
    nodes = [Node(i) for i in range(101)]
    cur = nodes[inp[0][0]]
    for line in inp[1:-1]:
        parent = line[0]
        branches = line[1:]
        for branch in branches:
            nodes[branch].parent = nodes[parent]

    path = f'{cur.val}'
    cur = cur.parent
    while cur != None:
        path += f' {cur.val}'
        cur = cur.parent
    return path

inp = [*map(lambda x: [*map(int, x.split())], sys.stdin)]
print(kitten(inp))