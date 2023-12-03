from collections import deque

n = int(input())
queue = list(map(int, input().split()))

queue = deque(queue)
a = []
maxValue = 0
while queue:
    left = queue[0]
    if maxValue < left:
        a.append("L")
        queue.popleft()
        maxValue = left
    if len(queue) > 0:
        right = queue[-1]
        if maxValue < right:
            a.append("R")
            queue.pop()
            maxValue = right
        if maxValue > left and maxValue > right:
            break

print(len(a))
print("".join(a))