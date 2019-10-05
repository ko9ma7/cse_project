'''
    2446. 별찍기 - 9

    예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
'''

class Stack:
    def __init__(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def peek(self):
        return self.items[len(self.items) - 1]

    def size(self):
        return len(self.items)

N = int(input())

stack = Stack()
str = ''
for i in range(2*N-1):
    str += '*'

for i in range(2*N-1):
    if i > (2*N-1)//2:
        s = stack.pop()
        for j in range((2*N-1)-i-1):
            print(' ', end='')
        print(s)
    elif i == (2*N-1)//2:
        for j in range(i):
            print(' ', end='')
        print(str[:(2*N-1)-2*i])
    else:
        stack.push(str[:(2*N-1)-2*i])
        for j in range(i):
            print(' ', end='')
        print(str[:(2*N-1)-2*i])