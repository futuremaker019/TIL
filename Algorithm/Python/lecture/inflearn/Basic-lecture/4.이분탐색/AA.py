n = int(input())
arr = []

for _ in range(n):
    p, k = map(int, input().split())
    arr.append((p, k))

cnt = 0
for i in range(n):
    tempCnt = 1
    closeTime = arr[i][1]
    loopCount = 0
    while loopCount < n:
        loopCount += 1
        for j in range(i, n):
            if closeTime == arr[j][0]:
                tempCnt += 1
                closeTime = arr[j][1]
    if cnt < tempCnt:
        cnt = tempCnt

print(cnt)