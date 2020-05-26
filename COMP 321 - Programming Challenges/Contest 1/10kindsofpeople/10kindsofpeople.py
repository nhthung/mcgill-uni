import sys

# def _10kindsofpeople(m, r1, c1, r2, c2):
#     print(r1,c1,r2,c2)

def find(l, x):
    while x != l[x]:
        x = l[x]
    return x

def unite(l, s, a, b):
    a = find(l, a)
    b = find(l, b)
    if a == b:
        return
    if s[a] < s[b]:
        a, b = b, a
    s[a] += s[b]
    l[b] = a

r, c = map(int, raw_input().split())
l = [i for i in range(r * c)]
s = [1]*r * c
a = [None] * r
for i in range(r):
    a[i] = map(int, raw_input())

# print s
for i in range(r):
    for j in range(c):
        if j < c - 1 and a[i][j] == a[i][j + 1]:
            unite(l, s, i * c + j, i * c + j + 1)
        if i < r - 1 and a[i][j] == a[i + 1][j]:
            unite(l, s, i * c + j, (i + 1) * c + j)

print s
print l

for _ in range(input()):
    r1, c1, r2, c2 = [int(x) - 1 for x in raw_input().split()]
    if find(l, r1 * c + c1) == find(l, r2 * c + c2):
        print 'decimal' if a[r1][c1] else 'binary'
    else: print 'neither'

# inp = [*map(str.strip, sys.stdin)]
# i = 0
# m = []
# r, c = [*map(int, inp[0].split())]
# m = inp[1:1+r]
# qq = inp[r+2:]
# for q in qq:
#     r1, c1, r2, c2 = [*map(int, q.split())]
#     print(_10kindsofpeople(m,r1,c1,r2,c2))