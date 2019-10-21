'''
    2439. 별찍기 - 2

    첫째 줄에는 별 1개, 둘째 줄에는 별 2개, N번째 줄에는 별 N개를 찍는 문제
'''

N = int(input())

star = ''
for i in range(N):
    star += '*'

idx = len(star)
j = N-1
for s in enumerate(star):
    for i in range(j, 0, -1):
        print(' ', end='')
    print(star[idx-1:])
    idx -= 1
    j -= 1