'''
    2438. 별찍기 - 1

    첫째 줄에는 별 1개, 둘째 줄에는 별 2개, N번째 줄에는 별 N개를 찍는 문제
'''

N = int(input())

star = ''
for i in range(N):
    star += '*'

for idx, s in enumerate(star):
    print(star[:idx+1])
    idx += 1