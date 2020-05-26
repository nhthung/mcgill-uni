import sys

def juice2(N, case):
    abc = [[0], [0], [0]]
    max_ppl = 0

    # Adding possible choices for a, b, c
    for i in range(3):
        for j in range(N):
            if case[j][i] not in abc[i]: # No duplicates
                abc[i].append(case[j][i])
    
    # Counting for all valid a, b, c combinations
    for a in abc[0]:
        for b in abc[1]:
            for c in abc[2]:
                if a + b + c > 10_000: continue
            
                count = 0
                for i in range(N):
                    if a >= case[i][0] and b >= case[i][1] and c >= case[i][2]:
                        count += 1
                
                # max_ppl = count if count > max_ppl
                max_ppl = max(count, max_ppl)
    return max_ppl


# Driver code
lines = sys.stdin.readlines()
case_idx = i = 1

while i < len(lines):
    N = int(lines[i])
    case = lines[i+1 : i+1+N]
    print(case)
    case = [* map(lambda x: [* map(int, x.strip().split())], case)]
    print(f'Case #{case_idx}: {juice2(N, case)}')

    i += N + 1
    case_idx += 1