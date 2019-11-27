import requests
import sys
sys.path.append("..")
from keys import WEATHER_REST_KEY
WEATHER_BASE_URL = "http://data.kma.go.kr/apiData/getData?"

if __name__ == "__main__":

    print('[기상청 종관 시간자료]')
    params = { 'type': 'json',
                 'dataCd': 'ASOS',
                 'dateCd': 'HR',
                 'startDt': '20171105',
                 'startHh': '00',
                 'endDt': '20171107',
                 'endHh': '23',
                 'stnIds': 108,
                 'schListCnt': 20, # 한 번에 출력할 수 있는 양
                 'pageIndex': 1,
                 'apiKey': WEATHER_REST_KEY }

    res = requests.get(url=WEATHER_BASE_URL, params=params)

    if res.status_code == 200:
        weathers = res.json()

        for weather in weathers:
            if 'info' in weather:
                for w in weather['info']:
                    print('관측시간: ', w['TM'])
                    print('관측지점번호: ', w['STN_ID'])
                    print('관측지점명: ', w['STN_NM'])
                    # print('관측 일사량: ', w['ICSR'])
                    print('관측 풍속: ', w['WS'])
                    print()
    else:
        print("Error {0}".format(res.status_code))