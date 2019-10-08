'''
    2447. 별찍기 - 10

    예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
    첫째 줄에 N이 주어진다. N은 항상 3의 제곱꼴인 수이다. (3, 9, 27, ...) (N=3k, 1 ≤ k < 8)
'''

def recursive(N):
    x = N
    y = N
    arr = [[0]*N for i in range(N)]

    print(arr)

    if x != 1 and y != 1:
        arr[x][y] = 1
    else:
        arr[x][y] = 0

recursive(3)