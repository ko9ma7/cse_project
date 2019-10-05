'''
    2447. 별찍기 - 10

    예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
    첫째 줄에 N이 주어진다. N은 항상 3의 제곱꼴인 수이다. (3, 9, 27, ...) (N=3k, 1 ≤ k < 8)
'''

def recursive(N):
    if N == 0:
        print('*', end='')
    else:
        recursive(N-1)

N = 9
for i in range(N*N):
    if i == 4:
        print(' ', end='')
    else:
        if i % N == 0 and i != 0:
            print()
        recursive(i)