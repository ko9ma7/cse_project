from pprint import pprint
import requests
from keys import KAKAO_REST_KEY
KAKAO_BASE_URL = "https://dapi.kakao.com"

if __name__ == "__main__":
    headers = {"Authorization" : "KakaoAK " + KAKAO_REST_KEY}
    res1 = requests.get(
        url=KAKAO_BASE_URL + "/v3/search/book?target=title&query=코스모스",
        headers=headers
    )

    if res1.status_code == 200:
        books = res1.json()
        for book in books['documents']:
            print(book)
            # print("{0:50s} - {1:20s}".format(str(book['title']), str(book['authors'])))
    else:
        print("Error {0}".format(res1.status_code))



    res2 = requests.get(
        url=KAKAO_BASE_URL + "/v2/search/image?query=아이린",
        headers=headers
    )

    if res2.status_code == 200:
        images = res2.json()
        for image in images['documents']:
            print(image['image_url'])
    else:
        print("Error {0}".format(res2.status_code))