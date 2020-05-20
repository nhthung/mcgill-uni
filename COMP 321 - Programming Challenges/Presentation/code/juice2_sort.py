import sys

def insertion_sort(arr, key, left, right):  
    for i in range(left + 1, right+1):  
        temp = arr[i]
        j = i - 1 
        while j >= left and arr[j][key] > temp[key]:  
            arr[j+1] = arr[j]
            j -= 1
        arr[j+1] = temp

def _insertion_sort(arr, left, right):  
    for i in range(left + 1, right+1):  
        temp = arr[i]
        j = i - 1 
        while j >= left and arr[j] > temp:  
            arr[j+1] = arr[j]
            j -= 1
        arr[j+1] = temp

def juice2(N, case):
    max_ppl = 0
    # Sort people by C value
    # case.sort(key=lambda x: x[2])
    insertion_sort(case, key=2, left=0, right=N-1)

    for i in range(N):
        # Let c be current C value we choose
        c = case[i][2]
        if i < N-1 and case[i+1][2] == c: continue
        # Select ppl satisfied with current C value
        # Sort them by their A value
        insertion_sort(case, key=0, left=0, right=i)

        # Let S be the set of people that are satisfied with current fractions of A, B, C
        S=[]
        # for j in range(len(A)):
        j_prev = 0
        for j in range(i+1):
            # Let a be current A value
            a = case[j][0]
            if j < i and j < N-1 and case[j+1][0] == a: continue
            # Let b be maximum B value under constraint
            b = 10_000-a-c
            if b < 0: break
            print(f'a,b,c: {a},{b},{c}')

            S_len = len(S)
            count = S_len
            # count = 0

            # As we increase the fraction of A, we can add current person to the set S
            # People in S are satisfied with fraction A and C.
            # S.append(case[j])
            # if case[j][1] not in S: S.append(case[j][1])
            print('before appending: ',end='')
            for k in range(j_prev, j+1):
                if case[k][1] <= b:
                    S.append(case[k][1])
                    count += 1
            j_prev = j+1
            print(S)
            print('after appending: ',end='')
            _insertion_sort(S, 0, len(S) - 1)
            # Remove people in S who are not satisfied with fraction B
            for k in range(S_len):
                # if S[k] > b: S[k] = -1
                # if S[k] == -1: count -= 1
                if S[k] > b: count -= 1
            print(S)
            print('count:', count)

            # Compare the number of people with max number of people we can satisfy so far
            max_ppl = max(count, max_ppl)
    return max_ppl

# Driver code
lines = sys.stdin.readlines()
case_idx = i = 1

while i < len(lines):
    N = int(lines[i])
    case = lines[i+1 : i+1+N]
    case = [* map(lambda x: [* map(int, x.strip().split())], case)]
    print(f'Case #{case_idx}: {juice2(N, case)}')

    i += N + 1
    case_idx += 1

# aa = [1,2,2,4,5,6,7,8,9,3]
# _insertion_sort(aa,0,0)
# for a in aa:
#     if a == 2:
#         aa.remove(a)
# print(len(aa)-aa.count(2))