import sys

def cold(n, ts):
    count = 0
    for t in ts:
        if t < 0:
            count += 1
    return count

inp = [*map(lambda x: [*map(int, x.split())], sys.stdin)]
print(cold(inp[0][0], inp[1]))