'''
    We will implement a general simulation of Hot Potato. Our program will input a list of names and a constant,
    call it “num,” to be used for counting. It will return the name of the last person remaining after repetitive counting by num.
    What happens at that point is up to you.

    To simulate the circle, we will use a queue. Assume that the child holding the potato will be at the front of the queue.
    Upon passing the potato, the simulation will simply dequeue and then immediately enqueue that child, putting her at the end of the line.
    She will then wait until all the others have been at the front before it will be her turn again.
    After num dequeue/enqueue operations, the child at the front will be removed permanently and another cycle will begin.
    This process will continue until only one name remains (the size of the queue is 1).
'''
from pythonds3 import Queue

def hotPotato(namelist, num):
    simqueue = Queue()

    for name in namelist:
        simqueue.enqueue(name)

    while simqueue.size() > 1:
        for i in range(num):
            simqueue.enqueue(simqueue.dequeue())

        # num만큼의 횟수가 지나면 그 사람이 hot potato를 가지고 있기 때문에 아웃이 된다.
        simqueue.dequeue()

    # 마지막에 남은 사람
    return simqueue.dequeue()

print(hotPotato(["Bill", "David", "Susan", "Jane", "Kent", "Brad"], 4))