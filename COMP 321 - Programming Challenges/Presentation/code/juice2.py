import sys

def juice2(N, case):
    # abc = [[0], [0], [0]]
    # abc = [None, None, None]
    max_ppl = 0
    # seen = {}
    seen = []

    # print(case)

    # Adding possible choices for a, b, c
    # In range(2), because only considering a, b pairs
    # for i in range(2):
    #     abc[i] = [case[j][i] for j in range(N)]
        # for j in range(N):
        #     abc[i].append(case[j][i])
            # if case[j][i] not in abc[i]:
            #     abc[i].append(case[j][i])
        # abc[i].sort()

    # print(abc)

    # Counting for all a, b, c combinations
    for i in range(N):
        a = case[i][0]
        for j in range(N):
            b = case[j][1]
            if (a, b) in seen:
                continue
            seen.append((a, b))
            c = 10_000 - a - b
            if c < 0: continue

            count = 0
            for k in range(N):
                if a >= case[k][0] and b >= case[k][1] and c >= case[k][2]:
                    count += 1
            max_ppl = max(count, max_ppl)

    # for a in abc[0]:
    #     for b in abc[1]:
    #         if a in seen and b in seen[a]:
    #             continue
    #         seen[a] = seen[a] + [b] if a in seen else [b]
    #         c = 10_000 - a - b
    #         if c < 0: continue

    #         count = 0
    #         for i in range(N):
    #             if a >= case[i][0] and b >= case[i][1] and c >= case[i][2]:
    #                 count += 1
    #         max_ppl = max(count, max_ppl)
    return max_ppl


# Driver code
lines = sys.stdin.readlines()
case_idx = i = 1

while i < len(lines):
    N = int(lines[i])
    case = lines[i+1 : i+1+N]
    case = [* map(lambda x: [*map(int, x.strip().split())], case)]
    print(f'Case #{case_idx}: {juice2(N, case)}')

    i += N + 1
    case_idx += 1