from datetime import datetime
import pandas as pd
from tabulate import tabulate
import sys
sys.path.insert(0, '../rest_server')
from read_electronic import read_electronic

def return_electronic_data():
    location = ['경기도', '강원도', '경상남도', '경상북도', '전라남도', '전라북도', '충청남도', '충청북도', '제주도',
                '서울특별시', '인천광역시', '대전광역시', '광주광역시', '대구광역시', '울산광역시', '부산광역시']
    electronic_total = read_electronic(location[0])

    administrative_area = []
    month_observation = []
    month_p_load = []
    for idx, loc in enumerate(location):

        if loc == '서울특별시':
            # 지역별로 나누기
            for electronic_data in electronic_total:
                for date in electronic_data:
                    observe = datetime.strptime(str(date), '%Y%m%d')

                    administrative_area.append(loc)
                    month_observation.append(str(observe.date()))
                    month_p_load.append(electronic_data[date])

    data = {
        'Administrative Area': administrative_area,
        'Observation': month_observation,
        'P_load': month_p_load
    }

    df = pd.DataFrame(data, columns=['Administrative Area', 'Observation', 'P_load'])
    # print(tabulate(df, headers='keys', tablefmt='psql'))
    return df