'''
    blueprint

    app.py에만 계속해서 넣을 수 없기 때문에 다른 파이썬 파일에서도 사용 가능하도록 해준다.
    flask 객체가 아닌 blueprint 객체가 필요하다.

    ex) blog.py

        @app.route('/blog/...')
'''
import requests
from flask import Blueprint, render_template

from keys import KAKAO_REST_KEY
from rest_client.read_kakao import KAKAO_BASE_URL

news_blueprint = Blueprint('news', __name__)

@news_blueprint.route('/')
def news():
    return 'Hellow News'

@news_blueprint.route('/main')
def news_main():
    return 'welcome news {0}'.format('JMH')

@news_blueprint.route('/sports')
def news_sports():
    return 'welcome sports news {0}'.format('JMH')

@news_blueprint.route('/images')
def images():
    images = ['a', 'b', 'c']
    headers = {"Authorization": "KakaoAK " + KAKAO_REST_KEY}
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
    return render_template(
        'images.html', images=images
    )

if __name__ == '__main__':
    # app.debug = True
    blueprint.run(host="localhost", port="8090")