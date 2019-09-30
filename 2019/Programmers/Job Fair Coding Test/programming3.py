"""
    한 줄로 붙어있는 스티커를 가위로 잘라 하나씩 보관하고 싶습니다.
    그러나 스티커 한 개를 깨끗하게 자르려면 인접해 있는 스티커들을 가위로 잘라야 합니다.
    예를 들어 위 그림에서 80이 적혀있는 스티커를 깨끗하게 자르고 싶으면 먼저 양쪽의 12와 14가 적힌 스티커의 점선 부분을
    가위로 잘라낸 후 80이 적힌 스티커의 가장자리를 다듬어야 합니다. 이때 12와 14가 적힌 스티커는 못 쓰게 됩니다.

    스티커에 적힌 숫자가 배열 sticker로 주어질 때, 깨끗하게 잘린 스티커에 적힌 숫자의 합이 최대가 되도록
    숫자의 합을 반환하는 함수를 완성해 주세요.
"""

# sticker는 배열
# 스티커의 길이는 1이상 100,000 이하입니다.
# 스티커에 적힌 숫자는 10,000 이하의 자연수 입니다.

from operator import itemgetter, attrgetter

def solution(sticker):

    data = []
    for index, s in enumerate(sticker):
        new = (index, s)
        data.append(new)

    new_data = sorted(data, key=itemgetter(1), reverse=True)

    new_data_1 = new_data[1:len(new_data)]

    sum_1 = 0
    check = []
    for index, d in enumerate(new_data):
        index = d[0]
        data = d[1]

        if index not in check and index + 1 not in check and index - 1 not in check:
            sum_1 += data
            check.append(index)

    sum_2 = 0
    check = []
    for index, d in enumerate(new_data_1):
        index = d[0]
        data = d[1]

        if index not in check and index + 1 not in check and index - 1 not in check:
            sum_2 += data
            check.append(index)

    if sum_1 > sum_2:
        answer = sum_1
    else:
        answer = sum_2

    return answer


sticker = [12, 80, 14, 22, 100]
solution(sticker)