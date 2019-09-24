import numpy as np
import scipy
import sklearn
from sklearn import datasets

# iris 데이터 출력
iris = datasets.load_iris()
print(iris.DESCR)
print(iris.target_names)

# 데이터와 목적 속성 지정 및 결과
X, y = iris.data, iris.target
print('Size of dta : %s' % (X.shape, ))
print('Target value : %s' % np.unique(y))

# 10개 데이터
print(X[0:10, :])

# sample 데이터가 어떤 꽃에 속하는지 예측해보자
sample = [[6, 4, 6, 2], ]

# k 최근접 이웃 알고리즘(kNN, k-Nearest Neighbors algorithm)
# 예측하고자 하는 데이터에 가까이 있는 k개의 데이터의 목적 속성을 보고 데이터를 예측하는 방법
# k = 3이면, 데이터에 가까이 있는 3개 데이터의 목적 속성에서 가장 많이 있는 것에 따라 예측한다.
# 주변 데이터에 따른 가중치와 주변 데이터를 계산할 알고리즘이 필요한데, 이 때 KNeighborsClassifier 클래스를 사용한다.
