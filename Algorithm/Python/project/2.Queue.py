import queue

# 라이브러리에서 queue를 가져온다.

data_queue = queue.Queue()

# put 메서드로 값을 추가
data_queue.put("coding")
data_queue.put(1)

print(data_queue.qsize())   # 2
print(data_queue.get())     # coding
print(data_queue.get())     # 1

# LifoQueue() (Last-in First-out)
dataLifoQueue = queue.LifoQueue()
dataLifoQueue.put("coding")
dataLifoQueue.put(1)

print(dataLifoQueue.get())  # 1
print(dataLifoQueue.get())  # coding

# PriorityQueue()
dataPriority = queue.PriorityQueue()
dataPriority.put((10, "korea"))
dataPriority.put((5, 1))
dataPriority.put((15, 10))

print(dataPriority.get())   # (5, 1)
print(dataPriority.get())   # (10, 'korea')
print(dataPriority.get())   # (15, 10)

# 연습1: 리스트 변수로 큐를 다루는 enqueue, dequeue 기능 구현
queue_list = list()

def enqueue(data) : 
    queue_list.append(data)

def dequeue() : 
    data = queue_list[0]
    del queue_list[0]
    return data

for index in range(10) : 
    enqueue(index)

len(queue_list)

print(dequeue())    # 독립적으로 실행을 해야 값을 얻을수 있음





