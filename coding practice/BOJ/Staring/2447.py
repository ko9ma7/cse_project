'''
    2447. 별찍기 - 10

    예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
    첫째 줄에 N이 주어진다. N은 항상 3의 제곱꼴인 수이다. (3, 9, 27, ...) (N=3k, 1 ≤ k < 8)
'''

class Re:
    def __init__(self, N):
        self.arr = [[' ']*N for i in range(N)]

    def recursive(self, N, x, y):
        if N == 1:
            self.arr[x][y] = '*'
        else:
            div = N//3
            for i in range(3):
                for j in range(3):
                    if i == 1 and j == 1:
                        pass
                    else:
                        print(div, x+(div*i), y+(div*j))
                        self.recursive(div, x+(div*i), y+(div*j))

    def getArr(self):
        return self.arr

N = int(input())
re = Re(N)
re.recursive(N, 0, 0)
arr = re.getArr()

for i in range(N):
    for j in range(N):
        print(arr[i][j], end='')
    print()