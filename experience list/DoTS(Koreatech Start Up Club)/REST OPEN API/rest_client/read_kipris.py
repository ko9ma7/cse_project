from pprint import pprint
import requests
from urllib.parse import urlencode, quote_plus
from urllib.request import Request, urlopen
from keys import KIPRIS_REST_KEY
import xml.etree.ElementTree as elemTree
KIPRIS_BASE_URL = "http://apis.data.go.kr/1430000/StdPatInfoService"

# getStdOrganInfo                 표준화 기구 정보조회
# getStdNoDeclareRecordPatInfo    표준 번호별 선언 / 등재특허 정보조회
# getDeclareRecordPatInfo         선언 / 등재 주체별 선언 / 등재특허 정보조회
# getTechFieldRecordPatInfo       기술분야별 선언 / 등재특허 정보조회
# getIndustryPolicyConnectInfo    산업 / 정책분류 연계 정보조회
# getDeclareRecordPatDetailInfo   선언 / 등재특허 상세 정보조회
# getStdPatCdInfo                 표준특허 코드정보조회

# url1 = 'http://apis.data.go.kr/1430000/StdPatInfoService/getDeclareRecordPatDetailInfo'
# queryParams = '?' + urlencode({ quote_plus('ServiceKey') : KIPRIS_REST_KEY,
#                                 quote_plus('numOfRows') : '10',
#                                 quote_plus('pageNo') : '1',
#                                 quote_plus('resultType') : 'xml',
#                                 quote_plus('patno_org') : 'EU1316958',
#                                 quote_plus('startdeclaredate') : '20120101',
#                                 quote_plus('enddeclaredate') : '20161231',
#                                 quote_plus('appno') : '03003494.6',
#                                 quote_plus('publicno') : '',
#                                 quote_plus('regno') : '' })

# url1 = 'http://apis.data.go.kr/1430000/StdPatInfoService/getDeclareRecordPatDetailInfo?pageNo=1&numOfRows=10&resultType=xml&startdeclaredate=20151128&enddeclaredate=20151128&serviceKey=e7QkPUk1he6L9GLScg5wEP8iyZ3zMORh%2FTx46J3PWMEz8rxfx6If%2FWoO7IJ398v0SJsk2krxkh0lm0B4Ks0ayw%3D%3D'
# request = Request(url1)
# request.get_method = lambda: 'GET'
# response_body = urlopen(request).read()
# pprint(response_body)

res1 = requests.get(
        url = 'http://openapi.kepco.co.kr/service/elecPowerContractUseService/getSidoList&ServiceKey=' + KIPRIS_REST_KEY
        # url = 'http://apis.data.go.kr/1430000/StdPatInfoService/getDeclareRecordPatDetailInfo?pageNo=1&numOfRows=10&resultType=xml&startdeclaredate=20151128&enddeclaredate=20151128&serviceKey=e7QkPUk1he6L9GLScg5wEP8iyZ3zMORh%2FTx46J3PWMEz8rxfx6If%2FWoO7IJ398v0SJsk2krxkh0lm0B4Ks0ayw%3D%3D'
    )
print(res1)
xmlStr = res1.text
print(xmlStr)
# root = elemTree.fromstring(xmlStr)
#
# if res1.status_code == 200:
#     result = root.find('result')
#     for items in result:
#         for item in items.iter("item"):
#             pass



# if __name__ == "__main__":
#
#     # 표준 번호별 선언 / 등재특허 정보조회
#     res1 = requests.get(
#         url=KIPRIS_BASE_URL + "/getStdNoDeclareRecordPatInfo?pageNo=1&numOfRows=1&resultType=json&declarercountry_adjust=중국&serviceKey=" + KIPRIS_REST_KEY,
#     )
#
#     if res1.status_code == 200:
#         print(res1)
#         # books = res1.json()
#         # for book in books['documents']:
#         #     print(book)
#         #     # print("{0:50s} - {1:20s}".format(str(book['title']), str(book['authors'])))
#     else:
#         print("Error {0}".format(res1.status_code))
#
#     # 기술분야별 선언 / 등재특허 정보조회
#
#     # 선언 / 등재특허 상세 정보조회
#
