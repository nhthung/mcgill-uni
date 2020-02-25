import sys

# def orders(menu, cost):
#     order_count = [0]
#     is_ambiguous = [False]
#     orders = []

#     def _orders(items, cost):
#         if cost == 0: # Found a permutation
#             items = sorted(items)
#             if order_count[0] == 1 and items not in orders:
#                 is_ambiguous[0] = True
#                 return
#             order_count[0] = 1
#             orders.append(items)

#         elif cost > 0:
#             for i in range(len(menu)):
#                 _orders(items + [str(i+1)], cost - menu[i])
    
#     _orders([], cost)
#     if len(orders) == 0:
#         return 'Impossible'
#     elif is_ambiguous[0]:
#         return 'Ambiguous'
#     return ' '.join(orders[0])

def orders(menu, cost):
    table = [0] * (cost+1)
    table[0] = 1

    lambda _table i: table[i] if i >= 0 else 0
    
    # for i in range(len(menu) + 1):
    #     for j in range(menu[i], cost+1):
    #         table[j] += table[j - menu[i]]

    # for i in range(cost+1):

    #     table[i] = min(table[i - price] + 1 for price in menu if price <= i)
    
    print(table)
    return table[cost]

inp = [*sys.stdin]
menu = [* map(int, inp[1].strip().split())]
order_costs = [* map(int, inp[3].strip().split())]

for cost in order_costs:
    print(menu, cost)
    print(orders(menu, cost))