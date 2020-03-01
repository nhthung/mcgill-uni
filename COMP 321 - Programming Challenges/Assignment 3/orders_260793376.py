'''
Task
You are to write a program that finds out what was ordered given the total cost of the order and the cost of each item on the menu.

Input
The input starts with a line containing one integer n (1≤n≤100), the number of items on the menu. The next line contains n space-separated positive integers c1,c2,…,cn, denoting the cost of each item on the menu in Swedish kronor. No item costs more than 1000 SEK.

This is followed by a line containing m (1≤m≤1000), the number of orders placed, and a line with m orders. Each order is given as an integer s (1≤s≤30000), the total cost of all ordered items in SEK.

Output
For each order in the input output one line as follows. If there is one unique order giving the specified total cost, output a space-separated list of the numbers of the items on that order in ascending order. If the order contains more than one of the same item, print the corresponding number the appropriate number of times. The first item on the menu has number 1, the second 2, and so on.

If there doesn’t exist an order that gives the specified sum, output Impossible. If there are more than one order that gives the specified sum, output Ambiguous.

Link: https://open.kattis.com/problems/orders
'''
import sys

def orders(costs, ordrs):
    # DP table: v[i] = -2 if no combination of items sums up to cost i ('Impossible')
    #                  -1 if more than one combination of items sums up to cost i ('Ambiguous')
    #                   k >= 0, if there's one unique combination, and item k led to cost i
    v = [-2]*(max(ordrs) + 1)
    
    # Initialize first cell, doesn't follow above rules
    v[0] = 0

    # Fill DP table according to above rules
    for item, cost in enumerate(costs):
        for i in range(len(v)):
            if v[i] >= 0 and i + cost < len(v):
                # Set cell to item index if first time reaching cell,
                # else set -1 for 'Ambiguous'
                v[i + cost] = item if v[i + cost] == -2 else -1
            
            elif v[i] == -1 and i + cost < len(v):
                # Set cell to -1 for 'Ambiguous', if previous cell in combination
                # is also -1
                v[i + cost] = -1
        
    def _order(order):
        if v[order] == -2:
            return 'Impossible'

        if v[order] == -1:
            return 'Ambiguous'
        
        # Traceback DP table to retrieve item combination
        items = []
        while order > 0:
            items.append(v[order] + 1)
            order -= costs[v[order]]
        return ' '.join(map(str, sorted(items)))        
    
    return '\n'.join(map(_order, ordrs))

inp = [*sys.stdin]

costs = [* map(int, inp[1].strip().split())]
ordrs = [* map(int, inp[3].strip().split())]

print(orders(costs, ordrs))