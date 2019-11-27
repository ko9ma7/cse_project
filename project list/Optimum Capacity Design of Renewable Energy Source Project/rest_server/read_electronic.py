from pprint import pprint

import requests
import sys
from bs4 import BeautifulSoup

sys.path.append("..")
from keys import ELECTRONIC_REST_KEY

ELECTRONIC_BASE_URL = "https://openapi.kpx.or.kr/openapi"

if __name__ == "__main__":

    print('[현재 전력 수급 현황]')
    res = requests.get(
        url=ELECTRONIC_BASE_URL + '/sukub5mMaxDatetime/getSukub5mMaxDatetime?ServiceKey=' + ELECTRONIC_REST_KEY
    )

    if res.status_code == 200:
        soup = BeautifulSoup(res.content, 'html.parser')
        print(soup)
        # electronics = soup.find_all('items')
        # pprint(electronics)

        # for electronic in electronics:
        #     if 'info' in electronic:
        #         for e in electronic['items']:
        #             print('기준일시: ', e['baseDatetime'])
        #             print('현재수요: ', e['currPwrTot'])
        #             print()
    else:
        print("Error {0}".format(res.status_code))