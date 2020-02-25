import sys

'''
Kattis: https://open.kattis.com/problems/color

Each of Luktas’ socks have a color Di which has a number between 0 and 109 assigned to it.
After some experimentation, he found that he could wash any socks with a maximum absolute
color difference of K in the same machine without any discoloring. The color difference
of two socks i and j is |Di−Dj|.

Luktas now needs to know how many washing machines he needs to wash his S socks,
given that each machine can take at most C socks a time.
'''
def color(num_socks, capacity, max_color_diff, socks):
    '''
    Return number of washin machines needed to wash all socks

    Parameters:
        num_socks     : total number of socks to be washed
        capacity      : max socks capacity per machine
        max_color_diff: max color difference allowed
        socks         : sock color values as int list
    '''
    # Machine counter
    num_machines = 1
    
    # Function returning absolute color difference of 2 socks
    def diff(color1, color2):
        return abs(color1 - color2)

    # Sort socks in ascending order
    socks = sorted(socks)

    # Pass through list once with 2 pointers
    i = 0
    j = i + 1
    while i < j < num_socks:
        if j - i + 1 > capacity or \
                diff(socks[j], socks[i]) > max_color_diff:
            # If excedeed machine capacity or max color difference,
            # add machine, move pointer i to j, and move pointer j to next position
            num_machines += 1
            i = j
            j += 1
        else:
            # Add sock in the machine
            j += 1

    return num_machines

# Convert stdin to list
inp = [
    [* map(int, line.strip().split())]
    for line in sys.stdin
]

print(color(*inp[0], inp[1]))