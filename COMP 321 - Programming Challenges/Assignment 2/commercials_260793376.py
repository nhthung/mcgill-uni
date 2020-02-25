import sys

'''
Kattis: https://open.kattis.com/problems/commercials

Of course, Onid Pizza has to pay a fixed amount every time the commercial
is played. The radio has a commercial break every 15 minutes. Unfortunately,
Onid can choose only one continuous sequence of commercial breaks, for example
all breaks from 5 pm to 8 pm. Help them to choose a continuous sequence of
commercial breaks such that their profit is maximal.
'''
def commercials(profits):
    '''
    Return max profit
    Parameters:
        profits: profits (revenue - cost) of all commerical breaks as int list
    '''
    max_profit = 0

    for i in range(1, len(profits)):
        # If the sum of profits from the start of
        # the commercial sequence until profits[i-1] is positive:
        #     profits[i] += profit of commercial sequence until i-1
        #
        # Else if profit of commercial sequence until i-1 is <= 0,
        # then end that commercial sequence, and start a new one:
        #     profits[i] unchanged
        profits[i] += max(profits[i-1], 0)

        # Save maximum commerical sequence profit
        if profits[i] > max_profit: max_profit = profits[i]
        
    return max_profit

inp = [[*map(int, line.strip().split())] for line in sys.stdin]
cost = inp[0][1]
profits = [*map(lambda revenue: revenue - cost, inp[1])]
print(commercials(profits))