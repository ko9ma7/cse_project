# stninfo에서 해당 지역에 따른 지점번호 -> 월별 데이터 REST 요청
import time
import sys

from tabulate import tabulate

sys.path.append("..")
from keys import WEATHER_REST_KEY
WEATHER_BASE_URL = "http://data.kma.go.kr/apiData/getData?"
import sys
sys.path.insert(0, '../rest_server')
from read_weather import read_weather
from pymongo import MongoClient
import stninfo
import pandas as pd

# 몽고 DB에 각종 정보 저장
def store_mongoDB(stnIds, stnNm, date, photovoltaic_data, wind_data):

    client = MongoClient('localhost:27017')
    db = client['JMH']

    collection_name = 'weather'
    collection = db[collection_name]

    weather_data = {
       'stn_id': stnIds,
       'stn_nm': stnNm,
       'tm': date,
       'icsr': photovoltaic_data,
       'ws': wind_data
    }

    collection.insert_one(weather_data)

def main():

    administrative_area = []
    autonomous_zone = []
    month_observation = []
    month_p_pv = []
    month_p_wind = []

    year = ['2017', '2018']
    # month = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']
    month = ['01', '02']

    # 관측시간 정하기
    for y in year:
        for m in month:

            startDt = y + m + '01'

            if m in ['01', '03', '05', '07', '08', '10', '12']:
                endDt = y + m + '31'
            elif m == '02': # 2017, 2018년은 평년
                endDt = y + m + '28'
            else:
                endDt = y + m + '30'

            for idx, key in enumerate(stninfo.stninfo):
                location_info = stninfo.stninfo[key]

                if key == '충청북도':
                    # 지역별로 나누기
                    for loc in location_info:
                        # print(loc)
                        stnIds = loc
                        stnNm, photovoltaic_total, wind_total = read_weather(stnIds, startDt, endDt)
                        # print(stnNm)
                        # print(photovoltaic_total)
                        # print(wind_total)
                        # print()

                        for photovoltaic, wind in zip(photovoltaic_total, wind_total):
                            # print('p_pv: ', photovoltaic)
                            # print('p_wind: ', wind)
                            for p, w in zip(photovoltaic, wind):
                                observe = p
                                # print(photovoltaic[observe])
                                # print(wind[observe])

                                administrative_area.append(key)
                                autonomous_zone.append(stnNm)

                                month_observation.append(observe)
                                month_p_pv.append(photovoltaic[observe])
                                month_p_wind.append(wind[observe])

                        time.sleep(2)
    # print()
    # print(administrative_area)
    # print(autonomous_zone)
    # print(month_observation)
    # print(month_p_pv)
    # print(month_p_wind)


    data = {
        'Administrative Area' : administrative_area,
        'Autonomous Zone': autonomous_zone,
        'Observation': month_observation,
        'P_pv': month_p_pv,
        'P_wind' : month_p_wind
    }

    df = pd.DataFrame(data, columns=['Administrative Area', 'Autonomous Zone', 'Observation', 'P_pv', 'P_wind'])
    print(tabulate(df, headers='keys', tablefmt='psql'))

if __name__ == "__main__":
    main()