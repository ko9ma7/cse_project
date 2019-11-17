import numpy as np
import scipy
import sklearn
from sklearn import datasets
import matplotlib
import matplotlib.pyplot as plt
from sklearn.neighbors import KNeighborsClassifier
from sklearn.dummy import DummyClassifier
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
from sklearn.model_selection import KFold
from sklearn import datasets

# iris 데이터 로드
iris = datasets.load_iris()
# print(iris.DESCR)
# print(iris.target_names)

# 데이터와 목적 속성 지정 및 결과
X, y = iris.data, iris.target
# print('Size of dta : %s' % (X.shape, ))
# print('Target value : %s' % np.unique(y))
# print()

# 10개 데이터
# print(X[0:10, :])
# print()

# sample 데이터가 어떤 꽃에 속하는지 예측해보자
sample = [[6, 4, 6, 2], ]

# k 최근접 이웃 알고리즘(kNN, k-Nearest Neighbors algorithm)
# 예측하고자 하는 데이터에 가까이 있는 k개의 데이터의 목적 속성을 보고 데이터를 예측하는 방법
# k = 3이면, 데이터에 가까이 있는 3개 데이터의 목적 속성에서 가장 많이 있는 것에 따라 예측한다.
# 주변 데이터에 따른 가중치와 주변 데이터를 계산할 알고리즘이 필요한데, 이 때 KNeighborsClassifier 클래스를 사용한다.

# predict 메소드에 샘플 입력
knn = KNeighborsClassifier(n_neighbors=2)
knn.fit(X, y) # X를 학습 데이터로, y를 목표 값으로 사용하여 모델을 맞춘다.
print(knn.score(X, y))

# DummyClassifier 모델
# 훈련 데이터에 가장 빈도가 높은 목적 속성이나 훈련 데이터 목적 속성의 분산에 따라 예측하는 DummyClassifier
dummy = DummyClassifier(strategy='stratified', random_state=0)
dummy.fit(X, y)
print(dummy.score(X, y))

# accuracy_score 함수로 평가하기
knn.fit(X, y)
print(accuracy_score(knn.predict(X), y))

# 최적의 k값 검색하기(8부터 20까지 어떤 k가 가장 최적일까)
result = []
score = []
k_range = range(8, 20)
for k in k_range:
    result.append(k)
    knn = KNeighborsClassifier(n_neighbors=k)
    knn.fit(X, y)
    score.append(knn.score(X, y))

    print('k is %d, score is %f' % (k, knn.score(X, y)))

# 결과 그래프 그리기
# plt.title('kNN')
# plt.xlabel('k')
# plt.ylabel('score')
# plt.plot(result, score, label='k value')
# plt.legend(loc='upper left')
# plt.show()

# 홀드 아웃(Hold out)과 교차 검증(Cross-validation)
# 과적합화를 탐지하기 위해 데이터를 훈련 데이터와 테스트 데이터를 나누어 평가하는 홀드 아웃을 사용한다.
# train_test_split 함수는 입력 받은 일정 비율로 데이터를 훈련 데이터와 테스트 데이터로 나눈다.

# train_test_split 함수를 이용해 훈련 데이터와 테스트 데이터 분할
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.5, random_state=0)

result = []
k_range = range(3, 20)
train_scores = []
test_scores = []
for k in k_range:
    result.append(k)
    knn = KNeighborsClassifier(n_neighbors=k)
    knn.fit(X_train, y_train)
    train_score = knn.score(X_train, y_train)
    train_scores.append(train_score)
    test_score = knn.score(X_test, y_test)
    test_scores.append(test_score)
    print('k is %d, train score=%f, test score=%f' % (k, train_score, test_score))

print("Train scores's mean %f" %(sum(train_scores)/len(k_range)))
print("Test scores's mean %f" %(sum(test_scores)/len(k_range)))

# 결과 그래프 그리기
# plt.title('train score vs test score')
# plt.xlabel('k')
# plt.ylabel('score')
# plt.plot(result, train_scores, label='train_score')
# plt.plot(result, test_scores, label='test_score', ls='--')
# plt.legend(loc='upper right')
# plt.show()

# 결과 그래프를 통해 데이터를 나눠 일부 데이터로 훈련하고 훈련할 때 사용하지 않은 나머지 데이터로 테스트 할 경우
# k가 15나 16에서 결과가 좋지 않다. 이를 통해 k가 15, 16일 때 모델이 훈련 데이터로 과적합화되어 있다는 사실을 알 수 있다.
# 따라서, 가지고 있는 데이터로 충분히 훈련하고 훈련 데이터 외의 데이터로 테스트하는 것이 모델에 효과적이다.
# 이를 위해 k 중첩 교차 검증(k fold cross validation) 방법을 사용한다. k는 전체 데이터를 나눌 개수이다.
# 예를 들어, k가 2라면 전체 데이터를 2로 나누고 첫 번째 데이터로 모델을 훈련하고 두 번째 데이터로 모델을 테스트한다.
# 그리고 또 다시 두 번째 데이터로 모델을 훈련하고 첫 번째 데이터로 모델을 테스트한다.

# KFold 함수로 교차 검증
folds = 5
kf = KFold(len(y), n_folds=folds, indices=True)

k_range = range(3, 20)
score_means = []
for k in k_range:
    test_scores = []
    knn = KNeighborsClassifier(n_neighbors=k)
    for train, test in kf:
        X_train, X_test, y_train, y_test = X[train], X[test], y[train], y[test]
        knn.fit(X_train, y_train)
        score = knn.score(X_test, y_test)
        test_scores.append(score)
        print('k is %d, test score is %f' %(k, score))

    score_means.append(sum(test_scores)/folds)
    print("k is %d, test scores's mean %f" %(k, sum(test_scores)/folds))
