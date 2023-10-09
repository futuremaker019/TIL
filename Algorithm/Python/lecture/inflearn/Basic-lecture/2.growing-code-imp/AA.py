T = int(input())                                # 테스트케이스

for j in range(T):
    N, s, e, k = list(map(int, input().split()))    
    arr = list(map(int, input().split()))
    arr = arr[s - 1 : e]
    arr.sort()

    count = 0
    for i in arr:
        count += 1
        if count == k:
            print("#" + str(j + 1) + " " + str(i))
            break