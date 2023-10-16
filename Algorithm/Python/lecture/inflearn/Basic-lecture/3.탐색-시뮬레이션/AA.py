n, m = list(map(int, input().split()))
a = list(map(int, input().split()))

cnt = 0
for i in range(n):
  for j in range(i + 1, n + 1):
    if sum(a[i:j]) == m:
      cnt += 1

print(cnt)