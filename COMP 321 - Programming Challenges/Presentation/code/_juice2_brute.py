import sys
lines = sys.stdin.readlines()
for i in range(len(lines)):
    lines[i] = lines[i].strip("\n").split()
    lines[i] = list(map(int,lines[i]))
numCases = lines[0][0]
countLine = 1

def check(numPeople,case):
    abc = [[0],[0],[0]]
    for i in range(numPeople):
        if i == 0:
            abc[0][0]=case[0][0]
            abc[1][0]=case[0][1]
            abc[2][0]=case[0][2]
        else:
            abc[0].append(case[i][0])
            abc[1].append(case[i][1])
            abc[2].append(case[i][2])
    for i in range (3):
        abc[i].sort()
    print(abc)
    a,b,c = 0,0,0
    max = 0
    for i in range(numPeople):
        for j in range(numPeople):
            for k in range (numPeople):
                a,b,c = abc[0][i],abc[1][j],abc[2][k]
                if a+b+c > 10000:
                    continue
                else:
                    count = 0
                    for l in range(numPeople):
                        if a>= case[l][0] and b>= case[l][1] and c>= case[l][2]:
                            count = count+1
                    if count > max:
                        max = count
    return max

for i in range(numCases):
    max = check(lines[countLine][0],lines[countLine+1:countLine+1+lines[countLine][0]])
    print("Case #",i+1,": ",max,sep='')
    countLine = countLine+1+lines[countLine][0]