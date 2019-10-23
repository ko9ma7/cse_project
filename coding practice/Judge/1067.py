'''
    1067. 팰린드롬(Palindrome) 길게 만들기

    팰린드롬이란 앞으로 읽으나 뒤로 읽으나 똑같은 걸 말합니다.
    문자열이 한 줄 주어질 때, 문자열을 재 배치 해서 팰린드롬을 만들 때 가장 길게 만들 수 있는 길이가 몇 인지 판단하는 프로그램을 만들어 주세요.

    # 입력
        첫 줄에는 테스트 케이스의 수 T (1 <= T <= 1000) 가 주어지며
        두번 째 줄부터 T개의 테스트 케이스로 문자열 (길이는 1자 이상 1000자 이하, 영문 대,소문자 만 주어짐)이 주어집니다.

    # 출력
        각 테스트 케이스마다 입력된 문자열을 재 배열 했을 때 가장 긴 팰린드롬의 길이가 몇 인지를 출력 해 주세요.
        대 소문자는 무시해도 됩니다. (즉 aA를 만들 수 있으면 길이 2인 팰린드롬을 만들 수 있습니다)
'''

import string
T = int(input())

for t in range(T):
    ans = input().lower()
    ans = sorted(ans)

    alpha = list(string.ascii_lowercase)

    sum = 0
    isOne = False
    for a in alpha:
        if ans.count(a) >= 2:
            sum += 2
        if ans.count(a) == 1:
            isOne = True

    if isOne:
        sum += 1

    print(sum)