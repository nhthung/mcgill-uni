#!/usr/bin/python3

import sys

def main():
    # Convert stdin into list
    inp = [*sys.stdin]

    # For each line of stdin
    for i, line in enumerate(inp):
        # Sanitize line
        line = line.strip().split()
        
        # If line is one integer, then it's the start of a series of operations
        if len(line) == 1:
            # Extract operations
            num_ops = int(line[0])
            ops = inp[i + 1 : i + 1 + num_ops]
            
            # Print data structure
            print(guess_dt(ops))


def guess_dt(ops):
    '''
    Return 'stack', 'queue', 'priority queue', 'impossible', or 'not sure'
    '''
    # Initialize possible data structures
    guesses = {
        'stack': 1,
        'queue': 1,
        'priority queue': 1
    }
    # Stack, queue, priority queue
    s, q, pq = [], [], []
    
    # Sanitize operations
    ops = [op.strip().split() for op in ops]

    # Execute each operation
    for op in ops:
        val = int(op[1])

        # If pushing, append value to each data structure
        if op[0] == '1':
            s.append(val)
            q.append(val)
            pq.append(val)

        # If popping, pop value from each data structure
        else: # op[0] == '2':
            # Can't pop value that wasn't in data structure
            if not (val in s or val in q or val in pq):
                return 'impossible'
            
            # Disqualify data structure if value not equal to popped value
            if s.pop(-1) !=  val:
                guesses['stack'] = 0
            if q.pop(0) !=  val:
                guesses['queue'] = 0
            if pq.pop(pq.index(max(pq))) != val:
                guesses['priority queue'] = 0
        
            # If none of the 3 data structures are correct, return 'impossible'
            if sum(guesses.values()) == 0:
                return 'impossible'

    # If more than one are possible, return 'not sure'
    if sum(guesses.values()) > 1:
        return 'not sure'
    
    # If only one data structure is possible, return that one
    for struct, guess in guesses.items():
        if guess == 1:
            return struct


if __name__ == '__main__':
    main()