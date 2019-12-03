from selenium import webdriver

chrome_options = webdriver.ChromeOptions()  # 크롬 옵션 객체 생성
chrome_options.add_argument('headless')  # headless 모드 설정
chrome_options.add_argument("--disable-gpu")  # gpu 허용 안함
chrome_options.add_argument("lang=ko_KR")  # 한국어 설정
chrome_options.add_argument('--no-sandbox')  # 페이지 탭 충돌 방지

# User-Agent 설정
chrome_options.add_argument(
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36"
)

driver = webdriver.Chrome('C:\chromedriver_win32\chromedriver.exe', chrome_options=chrome_options)

curr_url = 'https://news.naver.com/'
driver.get(curr_url)
driver.implicitly_wait(2)

news_kinds = driver.find_elements_by_class_name('tit_sec')
# news_urls = driver.find_elements_by_class_name('mtype_list_wide')
news_urls = driver.find_elements_by_css_selector('.no_bg')

for kinds in news_kinds:
    print(kinds.text)

for urls in news_urls:
    print(urls)
