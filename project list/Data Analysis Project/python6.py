'''
    pandas

    pandas는 NumPy의 스타일을 많이 차용했지만 가장 큰 차이점은 pandas는 표 형식의 데이터나 다양한 형태의 데이터를
    다루는 데 초점을 맞춰 설계했다는 것이다. NumPy는 단일 산술 배열 데이터를 다루는 데 특화되어 있다.
'''

import pandas as pd
import numpy as np
from pandas import Series, DataFrame

# Series는 일련의 객체를 담을 수 있는 1차원 배열 같은 자료구조
obj = pd.Series([4, 7, -5, 3])
print(obj)
print(obj.values)
print(obj.index)

obj2 = pd.Series([4, 7, -5, 3], index=['d', 'b', 'c', 'a'])
print(obj2)

# 단일 값을 선택하거나 여러 값을 선택할 때 색인으로 라벨을 사용할 수 있다.
print(obj2[['c', 'a', 'b']])

sdata = {'Ohio': 35000, 'Texas': 71000, 'Oregon': 16000, 'Utah': 5000}
obj3 = pd.Series(sdata)
print(obj3)

# 색인을 직접 정해주고 싶을 경우
states = ['California', 'Ohio', 'Oregon', 'Texas']
obj4 = pd.Series(sdata, index=states)
print(obj4)
print(pd.isnull(obj4))
print(pd.notnull(obj4))

obj4.name = 'population'
obj4.index.name = 'state'

# DataFrame은 표 같은 스프레드시트 형식의 자료구조이고 여러 개의 컬럼이 있는데 각 컬럼은
# 서로 다른 종류의 값을 담을 수 있다.
data = {'state': ['Ohio', 'Ohio', 'Ohio', 'Nevada', 'Nevada', 'Nevada'],
        'year': [2000, 2001, 2002, 2001, 2002, 2003],
        'pop': [1.5, 1.7, 3.6, 2.4, 2.9, 3.2]}
frame = pd.DataFrame(data)
frame2 = pd.DataFrame(data, columns=['year', 'state', 'pop'])
frame3 = pd.DataFrame(data, columns=['year', 'state', 'pop', 'debt'],
                      index=['one', 'two', 'three', 'four', 'five', 'six'])
print(frame3)
print(frame3.loc['three']) # loc 속성을 이용해서 이름을 통해 접근 가능

frame3['debt'] = np.arange(6.) # debt의 빈 값을 0.0부터 6.0까지 차례대로 채워진다.
print(frame3)

# 중첩된 사전을 이용해서 데이터 생성
pop = {'Nevada': {2001: 2.4, 2002: 2.9},
       'Ohio': {2000: 1.5, 2001: 1.7, 2002: 3.6}}
frame4 = pd.DataFrame(pop)
frame4.index.name = 'year'
frame4.columns.name = 'state'
print(frame4)
frame4 = frame4.T # 행/열 뒤바꿈
print(frame4)
print()
# 재색인
obj = pd.Series([4.5, 7.2, -5.3, 3.6], index=['d', 'b', 'a', 'c'])
print(obj)
obj2 = obj.reindex(['a', 'b', 'c', 'd', 'e'])
print(obj2)
print()

data = pd.DataFrame(np.arange(16).reshape((4, 4)),
                    index=['Ohio', 'Colorado', 'Utah', 'New York'],
                    columns=['one', 'two', 'three', 'four'])

print(data)
print(data.loc['Colorado', ['two', 'three']]) # 축 이름 선택
print(data.iloc[2, [3, 0, 1]]) # 정수 색인으로 축 이름 선택
print()

f = lambda x: x.max() - x.min() # Series의 최댓값과 최솟값의 차이를 계산하는 함수
frame5 = pd.DataFrame(np.random.randn(4, 3), columns=list('bde'), index=['Utah', 'Ohio', 'Texas', 'Oregon'])
print(frame5.apply(f))
print(frame5.apply(f, axis='columns'))

df = pd.DataFrame([[1.4, np.nan], [7.1, -4.5],
                   [np.nan, np.nan], [0.75, -1.3]],
                  index=['a', 'b', 'c', 'd'],
                  columns=['one', 'two'])

print(df)
print(df.sum()) # 각 컬럼의 합
print(df.sum(axis='columns')) # 각 로우의 합(axis=0이면 로우, 1이면 컬럼)
print(df.idxmax()) # 로우의 최댓값
print(df.idxmin()) # 로우의 최솟값
print(df.cumsum()) # 누산값