import requests
import xml.etree.ElementTree as elemTree
from keys import DBPIA_REST_KEY
DBPIA_BASE_URL = "http://api.dbpia.co.kr/v2/search/search.xml"

if __name__ == "__main__":

    url = DBPIA_BASE_URL + "?searchall=이순신&target=se&key=" + DBPIA_REST_KEY

    keyword = "데이터분석"
    res1 = requests.get(
        url = DBPIA_BASE_URL + "?searchall=" + keyword + "&target=se&key=" + DBPIA_REST_KEY,
    )

    xmlStr = res1.text
    root = elemTree.fromstring(xmlStr)

    if res1.status_code == 200:
        for element in root:
            for all_tags in element.findall('.//title'):
                print(all_tags.text)
            #
            # for all_tags in element.findall('.//'):
            #     # print('child: ', all_tags.tag, '|', all_tags.attrib)
            #     # print(all_tags.text)
            #
            #     if all_tags.tag == 'title':
            #         print("{0}".format(all_tags.text))
            #     if all_tags.tag == 'name':
            #         print(all_tags.text)
            #     if all_tags.tag == 'link_url':
            #         print(all_tags.text)
            #
            #     # if all_tags.text:
            #     #     print(all_tags.text, '|', all_tags.tail)

    else:
        print("Error {0}".format(res1.status_code))