"""
    n 개의 상품을 k개의 상자에 담아 포장하려고 합니다. 상품을 담을 때는, 상품의 크기보다 크거나 같은 상자에만 담을 수 있으며,
    상자 하나에는 한가지 상품만 담아야 합니다. 각 상품의 크기와 상자의 크기는 자연수로 나타냅니다.

    예를 들어, 상품 3개의 크기에 대한 정보가 담긴 배열 [5,3,7]이 주어졌을 때,
    첫 번째 상품의 크기는 5, 두 번째 상품의 크기는 3, 세 번째 상품의 크기는 7입니다.
    마찬가지로, 상자 3개의 크기가 담긴 정보가 담긴 배열 [3,7,6]이 주어졌을 때,
    첫 번째 상자의 크기는 3, 두 번째 상자의 크기는 7, 세 번째 상자의 크기는 6입니다.

    상품의 크기 goods = [5,3,7] 와 상자의 크기 boxes = [3,7,6] 일 때, 상품을 상자에 담는 방법의 하나는 다음과 같습니다.

        크기가 5인 첫 번째 상품을 크기가 7인 두 번째 상자에 담습니다.
        크기가 3인 두 번째 상품을 크기가 3인 첫 번째 상자에 담습니다.
        크기가 7인 세 번째 상품은 남은 상자 하나의 크기가 6이므로 담을 수 없습니다.

    이 상품들을 다음과 같은 방식으로 상자에 담으면, 모든 상품을 상자에 담아 포장할 수 있습니다.

        크기가 5인 첫 번째 상품을 크기가 6인 세 번째 상자에 담습니다.
        크기가 3인 두 번째 상품을 크기가 3인 첫 번째 상자에 담습니다.
        크기가 7인 세 번째 상품을 크기가 7인 두 번째 상자에 담습니다.

    이처럼 어떤 방식으로 상품을 상자에 담느냐에 따라 담을 수 있는 상품의 개수가 달라집니다.
    이때 최대한 많은 상품을 상자에 담아 포장하려고 합니다.

    상품들의 크기가 들어있는 배열 goods와 상자의 크기가 들어있는 배열 boxes가 매개변수로 주어질 때,
    상자에 넣을 수 있는 상품 개수의 최댓값을 return 하도록 solution 함수를 완성해주세요.

        *** 제한사항 ***

        배열 goods의 길이(상품의 개수 n)는 1 이상 100,000 이하의 자연수입니다.
        각 상품의 크기는 1 이상 200,000,000 이하의 자연수입니다.
        배열 boxes의 길이(상자의 개수 k)는 1 이상 100,000 이하의 자연수입니다.
        각 상자의 크기는 1 이상 200,000,000 이하의 자연수입니다.
"""

import pandas as pd

def solution(goods, boxes):

    goods = sorted(goods)
    boxes = sorted(boxes)

    df = pd.DataFrame(columns=boxes, index=goods)
    print(df)

    # 물건의 개수가 박스의 개수보다 클 경우
    if len(goods) > len(boxes):
        for g in goods:
            for b in boxes:
                if b >= g:
                    df.loc[g, b] = 1
                else:
                    df.loc[g, b] = 0

        print()
        print(df)

    # 물건의 개수가 박스의 개수와 같을 경우
    elif len(goods) == len(boxes):
        for g in goods:
            for b in boxes:
                if b >= g:
                    df.loc[g, b] = 1
                    print('goods: ', g, 'boxes: ', b)
                else:
                    df.loc[g, b] = 0

        print(df)

    # 물건의 개수가 박스의 개수보다 작을 경우
    else:
        for g in goods:
            for idx, b in enumerate(boxes):
                if b >= g:
                    df.loc[g, b] = 1
                    print('goods: ', g, 'boxes: ', b)
                else:
                    df.loc[g, b] = 0

        print()
        print(df)


goods = [5, 3, 7, 1, 3, 12]
boxes = [3, 7, 6, 3, 4, 12, 23]
solution(goods, boxes)