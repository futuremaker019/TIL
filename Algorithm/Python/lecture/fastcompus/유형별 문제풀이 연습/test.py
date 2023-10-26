t = int(input())
result = []

for _ in range(t):
  count = 1
  n, m = list(map(int, input().split()))
  papers = list(map(int, input().split()))

  queue = [(page, index) for index, page in enumerate(papers)]

  print(queue)

  while True:
    if queue[0][0] == max(queue, key = lambda x: x[0])[0]:
      if queue[0][1] == m:
        print(count)
        break
      else:
        queue.pop(0)
        count += 1
    else:
      queue.append(queue.pop(0))