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
              'schListCnt': 750, # 한 번에 출력할 수 있는 양(1 달치) 750
              'pageIndex': 1,
              'apiKey': WEATHER_REST_KEY}

    res = requests.get(url=WEATHER_BASE_URL, params=params)


    wind_total = []
    photovoltaic_total = []

    if res.status_code == 200:
        weathers = res.json()

        for weather in weathers:
            wind_by_date = {}
            photovoltaic_by_date = {}

            if 'info' in weather:
                wind_data = []
                photovoltaic_data = []
                cnt = 0

                # 24시간 주기
                for w in weather['info']:
                    date = w['TM'].split()[0]
                    if cnt == 24:
                        wind_data = []
                        photovoltaic_data = []
                        cnt = 0

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
                    cnt += 1

                    wind_by_date[date] = wind_data
                    photovoltaic_by_date[date] = photovoltaic_data

                wind_total.append(wind_by_date)
                photovoltaic_total.append(photovoltaic_by_date)
    else:
        print("Error {0}".format(res.status_code))

    print(wind_total)
    print(photovoltaic_total)

read_weather(177, '20170601', '20170630')

# start_year = '2017'
# start_month = '06'
# start_day = '01'
#
# end_year = '2018'
# end_month = '05'
# end_day = '31'
#
# for i in range(30):
#     read_weather(177, start_year+start_month+start_day, start_year+start_month+start_day)