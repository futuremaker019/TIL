arr = [list(map(int, input().split())) for _ in range(7)]
count = 0
for i in range(7):
    for j in range(3):
        if all(arr[i][j + k] == arr[i][j + 5 - k - 1] for k in range(2)):
            count += 1
        if all(arr[j + k][i] == arr[j + 5 - k - 1][i] for k in range(2)):
            count += 1

print(count)