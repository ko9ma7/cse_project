'''
    1. Create a queue of print tasks. Each task will be given a timestamp upon its arrival. The queue is empty to start.
    2. For each second (currentSecond):
        - Does a new print task get created? If so, add it to the queue with the currentSecond as the timestamp.
        - If the printer is not busy and if a task is waiting,
        - Remove the next task from the print queue and assign it to the printer.
        - Subtract the timestamp from the currentSecond to compute the waiting time for that task.
        - Append the waiting time for that task to a list for later processing.
        - Based on the number of pages in the print task, figure out how much time will be required.
        - The printer now does one second of printing if necessary. It also subtracts one second from the time required for that task.
        - If the task has been completed, in other words the time required has reached zero, the printer is no longer busy.
    3. After the simulation is complete, compute the average waiting time from the list of waiting times generated.
'''

from pythonds3 import Queue
import random

class Printer:
    def __init__(self, ppm):
        self.pagerate = ppm
        self.currentTask = None
        self.timeRemaining = 0

    # 작업이 완료되면 내부 타이머를 줄이고 프린터를 유휴 상태로 설정
    def tick(self):
        if self.currentTask != None:
            self.timeRemaining = self.timeRemaining - 1
            if self.timeRemaining <= 0:
                self.currentTask = None

    def busy(self):
        if self.currentTask != None:
            return True
        else:
            return False

    def startNext(self, newtask):
        self.currentTask = newtask
        self.timeRemaining = newtask.getPages() * 60/self.pagerate

# 각 작업은 대기 시간을 계산하기 위한 타임 스탬프를 유지해야 한다.
# 이 타임 스탬프는 작업이 생성되어 프린터 대기열에 배치된 시간을 나타낸다.
# waitTime 메소드는 인쇄가 시작되기 전에 큐에서 소요된 시간을 검색하기 위해 사용된다.
class Task:
    def __init__(self, time):
        self.timestamp = time
        self.pages = random.randrange(1, 21)

    def getStamp(self):
        return self.timestamp

    def getPages(self):
        return self.pages

    # 대기 시간 = 현재 시간 - 프린터 대기열에 배치된 시간(타임 스탬프)
    def waitTime(self, currenttime):
        return currenttime - self.timestamp

# numSeconds는 총 시뮬레이션 시간, pagesPerMinute은 페이지의 인쇄 속도(분)
def simulation(numSeconds, pagesPerMinute):
    # pagesPerMinute의 속도를 가지는 프린터 객체 생성
    labprinter = Printer(pagesPerMinute)
    # 인쇄 작업들을 계산하기 위한 대기 큐
    printQueue = Queue()
    waitingtimes = []

    for currentSecond in range(numSeconds):
        # 새로운 작업이 있는 경우(180초인 경우)
        if newPrintTask():
            # currentSecond에 생성된 작업을 대기 큐에 넣는다.
            task = Task(currentSecond)
            printQueue.enqueue(task)

        # 프린터가 바쁘지 않고 대기 큐에 작업이 있다면
        # 대기 큐에 있는 작업을 대기 열에 집어넣어 프린터가 계산하도록 한다.
        if (not labprinter.busy()) and (not printQueue.is_empty()):
            nexttask = printQueue.dequeue()
            waitingtimes.append(nexttask.waitTime(currentSecond))
            labprinter.startNext(nexttask)

        # 작업이 끝났을 경우 현재 작업은 사라진다.
        labprinter.tick()

    # 평균 대기 시간은 총 대기 시간을 작업들 개수만큼 나눠준다.
    averageWait = sum(waitingtimes) / len(waitingtimes)
    print('Average Wait %6.2f secs %3d tasks remaining.' %(averageWait, printQueue.size()))

# 새로운 인쇄 작업이 생겼는지 여부를 결정하는 함수
def newPrintTask():
    num = random.randrange(1, 181)
    # 인쇄 작업이 180초마다 한 번씩 도착하도록
    if num == 180:
        return True
    else:
        return False

for i in range(10):
    # 분당 작업 속도가 빠를 수록, 끝낼 수 있는 작업이 많아진다.
    simulation(3600, 10)