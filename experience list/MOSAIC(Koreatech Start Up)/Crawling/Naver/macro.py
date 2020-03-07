from bs4 import BeautifulSoup
import urllib.request
import time
import pyautogui

if __name__ == "__main__":
    loopIdx = 0
    loopLimit = 12000

    while True:
        uptempo = "https://smartstore.naver.com/neulhaerangmask/products/4632987981?site_preference=device&NaPm="
        req = urllib.request.Request(uptempo)
        res = urllib.request.urlopen(req)
        data = res.read()

        soup = BeautifulSoup(data.decode("utf-8"), 'html.parser')
        ready = False

        for span in soup.find_all("span"):
            if span.get('class') == None:
                continue

            if span.get('class')[0] == 'cart':
                for s in span:
                    if s.get('class')[0] == 'mask2':
                        continue
                    elif s.get('class')[0] == '_stopDefault':
                        ready = False
                    else:
                        ready = True
                        break

        if ready:
            x, y = pyautogui.position()
            print('x: {}, y: {}'.format(x, y))
            pyautogui.moveTo(x=840, y=889)
            pyautogui.click()
            break

        else:
            print(str(loopIdx) + " : 아직 준비안됨")

        loopIdx += 1
        time.sleep(0.5)

        if loopIdx > loopLimit:
            break
