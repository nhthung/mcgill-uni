import sys

max_n = 1_000_000
D = [False]*(max_n+1)

def bachet(n, ks):
    '''
    Return True if player moving first wins, else False
    '''
    for i in range(1, n+1):
        D[i] = False
        for k in ks:
            if i >= k and not D[i - k]:
                D[i] = True
                break
    return D[n]

inp = map(lambda x: [* map(int, x.strip().split())], sys.stdin)
players = ['Ollie', 'Stan']
for _inp in inp:
    _inp = [*_inp]
    print(players[int(bachet(_inp[0], _inp[2:]))], 'wins')