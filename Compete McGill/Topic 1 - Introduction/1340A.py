# n = 4
m = [0,4,2,3,1]
r = [0,1,3,3,0]
c = [0,1,0,2,0]
c_count = [0,1,1,0,0]
c_max = [2]

def step(i,n):
    r_val = r[m[i]]
    r_val_right = r[m[i]+1] if m[i]<n else 0
    print('r_val_right:',r_val_right)
    for j in range(m[i],0,-1):
        if r[j] != r_val: break
        r[j] = r_val_right
        c_count[c[j]] -= 1
    print('r:',r[1:5])
    
    
    if m[i] < n:
        print('c_count:',c_count[1:5])
        c_count[c[r_val_right]] -= 1
        print('c_count:',c_count[1:5])

        c[r_val_right] += c[m[i]]
        c_count[c[r_val_right]] += 1
        c_max[0] = max(c[r_val_right],c_max[0])
    
    c[m[i]] = 0
    if c_count[c_max[0]] == 0:
        for j in range(c_max[0],0,-1):
            if c_count[j] == 0: continue
            c_max[0] = j
            break

print('r:',r[1:5])
print('c:',c[1:5])
print('c_count:',c_count[1:5])
print('c_max:',c_max[0])
step(3,4)
print('c:',c[1:5])
print('c_count:',c_count[1:5])
print('c_max:',c_max[0])