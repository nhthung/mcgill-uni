#!/usr/bin/python3

import sys

# Convert stdin to list, sanitize each element
inp = [line.strip().split() for line in sys.stdin]

# Extract variables
w = int(inp[0][0])
p = int(inp[0][1])

# Add w to l for efficiency
l = [int(loc) for loc in inp[1]] + [w]

# Initialize output widths as all values in l
widths = [*l]

# Add to output widths all possible widths that are not yet in the output widths list
for i in range(len(l)):
    for j in range(i + 1,len(l)):
        # Candidate width calculated from difference of partition positions
        width = l[j] - l[i]

        # Only add new widths
        if width not in widths:
            widths.append(width)

# Sort widths, convert each element into string
widths = [str(w) for w in sorted(widths)]

# Convert into string of space-separated integers: final output
print(' '.join(widths))