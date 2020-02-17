"""
    행렬식의 여인수 전개

    A = a(i,j)인 n x n 행렬이 있을 때,

    1 <= i <= n에 대하여 i번째 행을 따라 여인수 전개를 한다면,
    det A = a(i,1)C(i,1) + a(i,2)C(i,2) + ... + a(i,n)C(i,n)

    1 <= j <= n에 대하여 j번째 행을 따라 여인수 전개를 한다면,
    det A = a(1,j)C(1,j) + a(2,j)C(2,j) + ... + a(n,j)C(n,j)

    즉, C(i,j) = (-1)^(i+j) x M(i,j)가 된다.

    1) 2차 행렬인 경우

        A = [[a, b],
             [c, d]]

        |A| = a|d| - b|c| = ad - bc

    2) 3차 행렬인 경우

        A = [[a, b, c],
             [d, e, f],
             [g, h, i]]

        |A| = a[[e, f], [h, i]] - b[[d, f], [g, i]] + c[[d, e], [g, h]]
            = aei + bfh + cdh - afh - bdi - ceg

    3) 4차 행렬인 경우

        A = [[a, b, c, d],
             [e, f, g, h],
             [i, j, k, l],
             [m, n, o, p]]

        |A| = a[[f, g, h], [j, k, l], [n, o, p]] - b[[e, g, h], [i, k, l], [m, o, p]]
                + c[[e, f, h], [i, j, l], [m, n, p]] - d[[e, f, g], [i, j, k], [m, n, o]]
"""

# 소행렬 M(i,j) 메소드
# 입력받은 idx의 값의 행, 열을 제외한 나머지 부분 반환
def get_M(m, idx, jdx):

    val = []
    for i in range(len(m)):
        v = []
        for j in range(len(m)):
            if i != idx and j != jdx:
                v.append(m[i][j])

        if v != []:
            val.append(v)

    return val

# 2차 행렬의 경우
def get_2dim_determinant(m):
    return m[0][0] * m[1][1] - m[0][1] * m[1][0]

# 3차 행렬의 경우
def get_3dim_determinant(m):
    return m[0][0] * get_2dim_determinant([[m[1][1], m[1][2]], [m[2][1], m[2][2]]]) \
         - m[0][1] * get_2dim_determinant([[m[1][0], m[1][2]], [m[2][0], m[2][2]]]) \
         + m[0][2] * get_2dim_determinant([[m[1][0], m[1][1]], [m[2][0], m[2][1]]])

import numpy as np

A = [[2, -1, 3, 0],
     [-3, 1, 0, 4],
     [-2, 1, 4, 1],
     [-1, 3, 0, -2]]

detA = 0
i = np.random.randint(len(A))
print('random row: {}'.format(i))

for k in range(len(A)):

    print('cofactor expansion({},{}): {}'.format(i, k, get_M(A, i, k)))
    detA += pow(-1, k) * A[i][k] * get_3dim_determinant(get_M(A, i, k))

print(detA)

# numpy 내장 함수 행렬식
anothor_detA = np.linalg.det(A)
print(anothor_detA)