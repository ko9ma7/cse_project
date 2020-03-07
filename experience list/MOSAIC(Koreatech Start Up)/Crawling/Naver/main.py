# -*- coding: utf-8 -*-
# from selenium import webdriver
#
# chrome_options = webdriver.ChromeOptions()  # 크롬 옵션 객체 생성
# chrome_options.add_argument('--headless')  # headless 모드 설정
# chrome_options.add_argument("--disable-gpu")  # gpu 허용 안함
# chrome_options.add_argument("lang=ko_KR")  # 한국어 설정
# chrome_options.add_argument('--no-sandbox')  # 페이지 탭 충돌 방지
# chrome_options.add_argument("--disable-dev-shm-usage")  # DevToolsActivePort file doesn't exist 에러 방지
#
# # User-Agent 설정
# chrome_options.add_argument(
#     "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.106 Safari/537.36"
# )
#
# print('--- Chromedriver loading ---')
# driver = webdriver.Chrome('C:\chromedriver_win32\chromedriver.exe', chrome_options=chrome_options)
# print('--- Chromedriver loaded ---')
#
# curr_url = 'https://smartstore.naver.com/neulhaerangmask/products/4632987981?site_preference=device&NaPm='
#
# driver.get(curr_url)
# driver.implicitly_wait(2)
# print('--- Driver setting complete ---')