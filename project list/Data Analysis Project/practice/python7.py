import pandas_datareader.data as web
import pandas as pd

all_data = {ticker: web.get_data_yahoo(ticker)
            for ticker in ['AAPL', 'IBM', 'MSFT', 'GOOG']}

price = pd.DataFrame({ticker: data['Adj Close']
                      for ticker, data in all_data.items()})

volume = pd.DataFrame({ticker: data['Volume']
                       for ticker, data in all_data.items()})

returns = price.pct_change()
print(returns.tail())

# corr 메소드는 NA가 아니며 정렬된 색인에서 연속하는 두 Series에 대해 상관관계를 계산하고 cov 메소드는 공분산을 계산한다.
returns['MSFT'].corr(returns['IBM'])
returns['MSFT'].cov(returns['IBM'])
print(returns.corr())
print(returns.cov())
print(returns.corrwith(returns.IBM))

# 시가총액의 퍼센트 변화율에 대한 상관관계
print(returns.corrwith(volume))

# 유일값, 값 세기, 멤버십
obj = pd.Series(['c', 'a', 'd', 'a', 'a', 'b', 'b', 'c', 'c'])
uniques = obj.unique()
print(uniques)
print(obj.value_counts()) # 빈도수

# Index.get_indexer 메소드는 여러 값이 들어있는 배열에서 유일한 값의 색인 배열을 구할 수 있다.
to_match = pd.Series(['c', 'a', 'b', 'b', 'c', 'a'])
unique_vals = pd.Series(['c', 'b', 'a'])

# to_match에 있는 배열의 값이 unique_vals에 있는 배열의 값과 일치하는 인덱스
print(pd.Index(unique_vals).get_indexer(to_match))

data = pd.DataFrame({'Q1': [1, 3, 4, 3, 4],
                     'Q2': [2, 3, 1, 2, 3],
                     'Q3': [1, 5, 2, 4, 4]})

# 결과값의 로우 레벨은 전체 컬럼의 유일한 값들을 담고 있고, 각 값은 각 컬럼에서 해당 값이 몇 번 출현했는지 나타낸다.
result = data.apply(pd.value_counts).fillna(0)
print(result)