'''
    2447. 별찍기 - 11

    예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.
    첫째 줄에 N이 주어진다. N은 항상 3×2k 수이다. (3, 6, 12, 24, 48, ...) (k ≤ 10)
'''

import math
DB = ['  *   ', ' * *  ', '***** ']

def recursive(shift):
    for i in range(len(DB)):
        DB.append(DB[i] + DB[i])
        DB[i] = "   " * shift + DB[i] + "   " * shift

n = int(input())
k = int(math.log(int(n//3), 2))
for i in range(k):
    recursive(int(pow(2, i)))

for i in range(n):
    print(DB[i])