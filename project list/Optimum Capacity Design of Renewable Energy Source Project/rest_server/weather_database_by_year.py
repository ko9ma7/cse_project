# stninfo에서 해당 지역에 따른 지점번호 -> 월별 데이터 REST 요청
import time
import sys
from pprint import pprint

from tabulate import tabulate
sys.path.append("..")
from keys import WEATHER_REST_KEY
WEATHER_BASE_URL = "http://data.kma.go.kr/apiData/getData?"
# import sys
# sys.path.insert(0, '../rest_server')
from read_weather_by_day import read_weather_by_day
import stninfo
import pandas as pd

def return_weather_data_by_year():

    administrative_area = []
    year_observation = []
    month_p_pv = []
    month_p_wind = []

    total_year_p_pv = []
    total_year_p_wind = []

    year = ['2017', '2018']
    month = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']

    # 관측시간 정하기
    for y in year:
        year_p_pv = [0] * 24
        year_p_wind = [0] * 24

        year_observation.append(y)

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

                if key == '경기도':
                    # 지역별로 나누기
                    for loc in location_info:
                        # print(loc)
                        stnIds = loc
                        photovoltaic_total, wind_total = read_weather_by_day(stnIds, startDt, endDt)
                        sum_photovoltaic = [0] * 24
                        sum_wind = [0] * 24

                        # 태양광 에너지 한 달치 평균값
                        for photovoltaic in photovoltaic_total:
                            days = len(photovoltaic)

                            for idx, p in enumerate(photovoltaic):
                                # 값이 24개가 안되는 경우
                                while len(photovoltaic[p]) != 24:
                                    photovoltaic[p].append(0)

                                for jdx in range(len(photovoltaic[p])):
                                    sum_photovoltaic[jdx] += photovoltaic[p][jdx]

                            for kdx in range(len(sum_photovoltaic)):
                                sum_photovoltaic[kdx] /= days
                                sum_photovoltaic[kdx] = round(sum_photovoltaic[kdx], 3)

                        # 풍력 에너지 한 달치 평균값
                        for wind in wind_total:
                            days = len(wind)

                            for idx, w in enumerate(wind):
                                # 값이 24개가 안되는 경우
                                while len(wind[w]) != 24:
                                    wind[w].append(0)

                                for jdx in range(len(wind[w])):
                                    sum_wind[jdx] += wind[w][jdx]

                            for kdx in range(len(sum_wind)):
                                sum_wind[kdx] /= days
                                sum_wind[kdx] = round(sum_wind[kdx], 3)


                        month_p_pv.append(sum_photovoltaic)
                        month_p_wind.append(sum_wind)

                        time.sleep(1)

        administrative_area.append(key)

        for p_pv, p_wind in zip(month_p_pv, month_p_wind):

            idx = 0
            for p, w in zip(p_pv, p_wind):
                year_p_pv[idx] += p
                year_p_wind[idx] += w

                year_p_pv[idx] = round(year_p_pv[idx], 4)
                year_p_wind[idx] = round(year_p_wind[idx], 4)

                idx += 1

        total_year_p_pv.append(year_p_pv)
        total_year_p_wind.append(year_p_wind)

    # print(administrative_area)
    # print(year_observation)
    # print(total_year_p_pv)
    # print(total_year_p_wind)

    data = {
        'Administrative_Area' : administrative_area,
        'Observation': year_observation,
        'P_pv': total_year_p_pv,
        'P_wind' : total_year_p_wind
    }

    df = pd.DataFrame(data, columns=['Administrative_Area', 'Observation', 'P_pv', 'P_wind'])
    # print(tabulate(df, headers='keys', tablefmt='psql'))
    return df