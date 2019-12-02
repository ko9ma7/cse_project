import sys
import pandas as pd
from tabulate import tabulate

sys.path.append("..")

if __name__ == "__main__":
    electronics_csv = pd.read_csv('../database/electronics.csv', encoding='euc-kr') # 2017, 2018 지역별 시간단위 발전량
    # print(electronics_csv.shape)
    # print(electronics_csv.columns)
    print(electronics_csv)

    location = '경기도'
    electronics_data = electronics_csv.loc[:, ['거래일자', '거래시간', location]] # 행, 열
    electronic = {}
    observe = electronics_data.head(24)['거래일자'][0]

    elec_data = []
    for idx, data in enumerate(electronics_data.head(24)[location]):
        elec_data.append(data)

    electronic[observe] = elec_data
    print(electronic)