'''
    2447. 별찍기 - 11

    예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
    첫째 줄에 N이 주어진다. N은 항상 3×2k 수이다. (3, 6, 12, 24, 48, ...) (k ≤ 10)
'''

def Re(N):
    if N == 3:
        for x in range(N):
            for i in range(N-x-1):
                print(' ', end='')
            for y in range(2*x+1):
                if x == 1 and y == 1:
                    print(' ', end='')
                else:
                    print('*', end='')
            print()


Re(3)