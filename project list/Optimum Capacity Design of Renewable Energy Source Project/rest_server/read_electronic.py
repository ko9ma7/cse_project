from pprint import pprint
from xml.etree import ElementTree

import requests
import sys
import pandas as pd
from bs4 import BeautifulSoup
import xml.etree.ElementTree as ET

sys.path.append("..")
from keys import ELECTRONIC_REST_KEY

ELECTRONIC_BASE_URL = "https://openapi.kpx.or.kr/openapi"

if __name__ == "__main__":
    electronics_csv = pd.read_csv('../database/electronics.csv', encoding='euc-kr') # 2017, 2018 지역별 시간단위 발전량
    print(electronics_csv.shape)
    print(electronics_csv)

