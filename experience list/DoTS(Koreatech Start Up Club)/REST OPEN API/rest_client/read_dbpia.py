import requests
import xml.etree.ElementTree as elemTree
from keys import DBPIA_REST_KEY
DBPIA_BASE_URL = "http://api.dbpia.co.kr/v2/search/search.xml"

if __name__ == "__main__":

    keyword = input('input keyword: ')
    res1 = requests.get(
        url = DBPIA_BASE_URL + "?searchall=" + keyword + "&target=se&key=" + DBPIA_REST_KEY,
    )

    xmlStr = res1.text
    root = elemTree.fromstring(xmlStr)

    if res1.status_code == 200:
        result = root.find('result')
        for items in result:
            for item in items.iter("item"):
                # 논문 제목
                print('제목: ', item.findtext('title'))
                # 논문 저자
                print('저자: ', end='')
                for author in item.find('authors'):
                    print(author.findtext('name'), end=' ')
                print()
                # 논문 url
                print('URL: ', item.findtext('link_url'))
                print()
    else:
        print("Error {0}".format(res1.status_code))