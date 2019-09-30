'''
    데이터 분석에서 중요한 기능

    1. 벡터 배열 상에서 데이터 가공, 정제, 부분집합, 필터링, 변형 그리고 다른 여러 종류의 연산을 빠르게 수행
    2. 정렬, 유일 원소 찾기, 집합 연산 같은 일반적인 배열 처리 알고리즘
    3. 통계의 효과적인 표현과 데이터를 수집 요약하기
    4. 다양한 종류의 데이터를 병합하고 엮기 위한 데이터 정렬과 데이터 간의 관계 조작
    5. 내부에서 if-elif-else를 사용하는 반복문 대신 사용할 수 있는 조건절 표현을 허용하는 배열 처리
    6. 데이터 묶음 전체에 적용할 수 있는 수집, 변형, 함수 적용 같은 데이터 처리


    NumPy

    1. NumPy는 내부적으로 데이터를 다른 내장 파이썬 객체와 구분된 연속된 메모리 블록에 저장한다.
    2. NumPy의 각종 알고리즘은 모두 C로 작성되어 타입 검사나 다른 오버헤드 없이 메모리를 직접 조작할 수 있다.
    3. NumPy 배열은 내장 파이썬의 연속된 자료형들보다 훨씬 더 적은 메모리를 사용한다.
    4. NumPy 연산은 파이썬 반복문을 사용하지 않고 전체 배열에 대한 복잡한 계산을 수행할 수 있다.
'''

import numpy as np
from numpy.linalg import inv, qr # 행렬의 분할과 역행렬, 행렬식

'''
data1 = [6, 7.5, 8, 0, 1]
arr1 = np.array(data1)
print(arr1)

data2 = [[1, 2, 3, 4], [5, 6, 7, 8]]
arr2 = np.array(data2)
print(arr2)

# zeros는 0으로 채워진 배열을 생성, ones는 1로 채워진 배열을 생성, empty는 초기화되지 않은(쓰레기 값) 배열을 생성
zero_data = np.zeros(10)
print(zero_data)

one_data = np.ones(10)
print(one_data)

empty_data = np.empty((3, 3))
print(empty_data)

# eye는 N x N 크기의 단위 행렬을 생성
eye_data = np.eye(3, 3)
print(eye_data)

# boolean 값으로 선택
names = np.array(['Bob', 'Joe', 'Will', 'Bob', 'Will', 'Joe', 'Joe'])
data = np.random.randn(7, 4)
print(data)
print()

print(data[names == 'Bob'])

mask = (names == 'Bob') | (names == 'Will')
print(data[mask])

arr = np.arange(15).reshape(3, 5)
print(arr)
print(arr.T)

arr = np.arange(16).reshape((2, 2, 4))
print(arr)
print(arr.transpose((1, 0, 2))) # 첫 번째와 두 번째 축 순서가 뒤바뀜
print(arr.swapaxes(1, 2)) # 두 개의 축 번호를 받아서 배열을 뒤바꿈

arr = np.random.randn(7) * 5
print(arr)
remainder, whole_part = np.modf(arr)
print(remainder)
print(whole_part)

arr = np.random.randn(4, 4)
print(arr)
print(np.where(arr > 0, 2, -2)) # 양수는 모두 2로, 음수는 모두 -2로 바꾸기

# 선형대수
x = np.array([[1., 2., 3.], [4., 5., 6.]])
y = np.array([[6., 23.], [-1, 7], [8, 9]])
print(x)
print(y)
print(x.dot(y)) # np.dot(x, y)와 같음
'''

X = np.random.randn(5, 5)
mat = X.T.dot(X)
mat = inv(mat) # inv는 정사각 행렬의 역행렬을 구한다.
print(mat)

q, r = qr(mat)
print(r)