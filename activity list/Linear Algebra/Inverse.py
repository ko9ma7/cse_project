"""
    [문제]

    가우스 소거법을 통해 역행렬을 구하는 함수를 작성하시오.
    그 함수를 사용하여 행렬 A의 역행렬을 구하시오

    A = [[2, 2, 0],
         [-2, 1, 1],
         [3, 0, 1]]

    step1) 행렬의 옆에 같은 차수의 항등 행렬을 위치시킨다.
    step2) A[1, 1]을 1로 만들도록 1행 전체를 A[1, 1] 성분으로 나눈다.
    step3) A[2, 1]을 0으로 만들도록 1행 전체를 -(A[2, 1]) 성분을 곱한 값을 2행과 더한다.
    step4) A[3, 1]과 A[3, 2]를 0으로 만들도록 위 과정을 반복한다.
"""
import numpy as np

A = np.array([[2, 2, 0], [-2, 1, 1], [3, 0, 1]], dtype=np.float64)

def inverse(A):
    C = np.array([[1, 0, 0], [0, 1, 0], [0, 0, 1]], dtype=np.float64)

    for i in range(len(A)):
        # A[1, 1]을 1로 만들도록 1행 전체를 A[1, 1] 성분으로 나눈다.
        if A[0][0] != 1:
            C[0] = [C[0][j] / A[0][0] for j in range(len(C[0]))]
            A[0] = [A[0][j] / A[0][0] for j in range(len(A[0]))]
        # 두 번째 행부터는 그 순서의 해당하는 항이 1이 되어야 한다.
        if A[i][i] != 1 or A[i][i-1] != 0:
            print(A[i])
            p = [A[i-1][j] * (-(A[i][i-1])) for j in range(len(A[i-1]))]
            print(p)

inverse(A)

