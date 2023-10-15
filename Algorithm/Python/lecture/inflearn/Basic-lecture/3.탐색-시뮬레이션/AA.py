n = int(input())

result = []
for i in range(n):
    value = input()
    value = value.lower()
    check = True
    for j in range(len(value) // 2):
        if value[j] != value[len(value) - j - 1]:
            check = False
        
    if check:
        result.append('#{i} YES'.format(i = i + 1))
    else:
        result.append('#{i} NO'.format(i = i + 1))

for i in result:
    print(i)