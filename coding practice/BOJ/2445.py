'''
    2445. 별찍기 - 8

    예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
'''

N = int(input())

str = ''
for i in range(2*N):
    str += '*'

k = (2*N-1)//2
for i in range(2*N-1):
    if i > (2*N-1)//2:
        print(str[:k], end='')
        for j in range((i-(2*N-1)//2)*2):
            print(' ', end='')
        print(str[-k:])
        k -= 1
    elif i == k:
        print(str)
    else:
        print(str[:i+1], end='')
        for j in range(2*N-2*(i+1)):
            print(' ', end='')
        print(str[-(i+1):])