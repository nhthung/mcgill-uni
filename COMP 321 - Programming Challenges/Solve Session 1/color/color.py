import sys

def color(num_socks, capacity, max_color_diff, socks):
    '''
    capacity: max socks capacity per machine 
    '''
    num_machines = 0
    
    def diff(color1, color2):
        return abs(color1 - color2)

    socks = sorted(socks)

    i = 0
    j = i + 1
    while i < j < num_socks:
        if j - i + 1 > capacity or diff(socks[j], socks[i]) > max_color_diff:
            num_machines += 1
            i = j
            j += 1
        else:
            j += 1

    return num_machines + 1

inp = [
    [* map(int, line.strip().split())]
    for line in sys.stdin
]

print(color(*inp[0], inp[1]))