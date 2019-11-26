from pprint import pprint
import requests
import os
import sys
sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))
from . import keys
WEATHER_BASE_URL = "http://data.kma.go.kr"
# http://data.kma.go.kr/apiData/getData?type=xml&dataCd=ASOS&dateCd=HR&startDt=20100101&startHh=09&endDt=20100102&endHh=04&stnIds=108&schListCnt=10&pageIndex=1&apiKey=사용자api키

if __name__ == "__main__":
    headers = {"Authorization" : "KakaoAK " + WEATHER_REST_KRY}
    res = requests.get(
        url=WEATHER_BASE_URL + "/apiData/getData?",
        type='json',
        dataCd='ASOS',
        dateCd='HR',
        startDt='20100101',
        startHh='09',
        endDt='20100102',
        endHh='04',
        stnIds='108',
        schListCnt='10',
        pageIndex='1',
        apiKey=WEATHER_REST_KRY,
    )

    if res.status_code == 200:
        weathers = res.json()
        print(weathers)
        # for book in books['documents']:
        #     print(book)
        #     # print("{0:50s} - {1:20s}".format(str(book['title']), str(book['authors'])))
    else:
        print("Error {0}".format(res.status_code))


