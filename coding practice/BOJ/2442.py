'''
    2442. 별찍기 - 5

    첫째 줄에는 별 1개, 둘째 줄에는 별 3개, ..., N번째 줄에는 별 2×N-1개를 찍는 문제
    별은 가운데를 기준으로 대칭이어야 한다.
'''

N = int(input())

str = ''
for i in range(2*N-1):
    str += '*'

j = 0
for i in range(N):
    for j in range(N-i-1):
        print(' ', end='')
    print(str[:2*i+1])