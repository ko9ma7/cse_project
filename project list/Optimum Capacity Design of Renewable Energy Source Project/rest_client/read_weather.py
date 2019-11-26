import requests
import sys
sys.path.append("..")
from keys import WEATHER_REST_KEY
WEATHER_BASE_URL = "http://data.kma.go.kr/apiData/getData?"

if __name__ == "__main__":

    print('[기상청 종관 시간자료]')
    params1 = { 'type': 'json',
                 'dataCd': 'ASOS',
                 'dateCd': 'HR',
                 'startDt': '20100203',
                 'startHh': '00',
                 'endDt': '20100204',
                 'endHh': '23',
                 'stnIds': 108,
                 'schListCnt': 10,
                 'pageIndex': 1,
                 'apiKey': WEATHER_REST_KEY }
    res1 = requests.get(url=WEATHER_BASE_URL, params=params1)

    if res1.status_code == 200:
        weathers1 = res1.json()

        for weather in weathers1:
            if 'info' in weather:
                for w in weather['info']:
                    print('관측시간: ', w['TM'])
                    print('관측지점번호: ', w['STN_ID'])
                    print('관측지점명: ', w['STN_NM'])
                    print('관측 일사량: ', w['ICSR'])
                    print('관측 풍속: ', w['WS'])
                    print()
    else:
        print("Error {0}".format(res1.status_code))

    print()
    print('[기상청 종관 일자료]')
    params2 = { 'type': 'json',
                'dataCd': 'ASOS',
                'dateCd': 'DAY',
                'startDt': '20100203',
                'endDt': '20100204',
                'stnIds': 108,
                'schListCnt': 10,
                'pageIndex': 1,
                'apiKey': WEATHER_REST_KEY}
    res2 = requests.get(url=WEATHER_BASE_URL, params=params2)

    if res2.status_code == 200:
        weathers2 = res2.json()

        for weather in weathers2:
            if 'info' in weather:
                for w in weather['info']:
                    print('관측시간: ', w['TM'])
                    print('관측지점번호: ', w['STN_ID'])
                    print('관측지점명: ', w['STN_NM'])
                    print('최대 풍속: ', w['MAX_WS'])
                    print('1시간 최다 일사량: ', w['HR1_MAX_ICSR'])
                    print()

    else:
        print("Error {0}".format(res2.status_code))

