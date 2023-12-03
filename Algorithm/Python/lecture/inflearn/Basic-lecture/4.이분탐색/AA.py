n, m = map(int, input().split())
queue = list(map(int, input().split()))

queue.sort()

cnt = 0
while queue:
    w1 = queue[0]
    w2 = queue[-1]
    if m >= w1 + w2:
        queue.pop(0)
        queue.pop()
        cnt += 1
    else:
        queue.pop()
        cnt += 1

print(cnt)