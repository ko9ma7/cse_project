from pprint import pprint

import requests
import sys
sys.path.append("..")
from keys import WEATHER_REST_KEY
WEATHER_BASE_URL = "http://data.kma.go.kr/apiData/getData?"

def read_weather(stnIds, startDt, endDt):
    params = {'type': 'json',
              'dataCd': 'ASOS',
              'dateCd': 'HR',
              'startDt': startDt,
              'startHh': '00',
              'endDt': endDt,
              'endHh': '23',
              'stnIds': stnIds,
              'schListCnt': 530, # 한 번에 출력할 수 있는 양
              'pageIndex': 1,
              'apiKey': WEATHER_REST_KEY}

    res = requests.get(url=WEATHER_BASE_URL, params=params)

    # 24시간 주기


    photovoltaic = {}
    photovoltaic_data = []

    wind_total = []

    if res.status_code == 200:
        weathers = res.json()

        for weather in weathers:
            wind_by_date = {}
            if 'info' in weather:
                wind_data = []
                cnt = 0
                for w in weather['info']:
                    date = w['TM'].split()[0]
                    if cnt == 24:
                        wind_by_date[date] = wind_data
                    else:
                        print('관측시간: ', w['TM'])
                        if 'ICSR' in w:
                            print('관측 일사량: ', w['ICSR'])
                            photovoltaic_data.append(w['ICSR'])
                        else:
                            print('관측 일사량: ', 0)
                            photovoltaic_data.append(0)
                        if 'WS' in w:
                            print('관측 풍속: ', w['WS'])
                            wind_data.append(w['WS'])
                        else:
                            wind_data.append(0)

                wind_total.append(wind_by_date)
    else:
        print("Error {0}".format(res.status_code))

    # wind[startDt] = wind_data
    # photovoltaic[startDt] = photovoltaic_data

    print(wind_total)
    # print(photovoltaic)

read_weather(177, '20170601', '20170603')