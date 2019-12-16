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
from views.auth import kakao_oauth

menus_blueprint = Blueprint('menus', __name__)

@menus_blueprint.route('/')
def news():
    return 'Hellow News'

@menus_blueprint.route('/main')
def menus_main():
    return 'welcome news {0}'.format('JMH')

@menus_blueprint.route('/books')
def books():
    headers = {"Authorization": "KakaoAK " + KAKAO_REST_KEY}
    res1 = requests.get(
        # url="http://localhost:8080/resource/t1", ===> 카카오에서 직접 가져오는 것이 아니라 내가 가공해서 다시 제공해주어야 한다.
        url=KAKAO_BASE_URL + "/v3/search/book?target=title&query=자이스토리",
        headers=headers
    )

    if res1.status_code == 200:
        docs = res1.json()
        books = docs['documents']
        # for book in docs['documents']:
        #     books.append(str(book['title']))

            # print("{0:50s} - {1:20s}".format(str(book['title']), str(book['authors'])))

    else:
        print("Error {0}".format(res1.status_code))

    return render_template(
        'books.html', books=books, nav_menu="book", kakao_oauth=kakao_oauth
    )

@menus_blueprint.route('/images')
def images():
    headers = {"Authorization": "KakaoAK " + KAKAO_REST_KEY}
    res2 = requests.get(
        url=KAKAO_BASE_URL + "/v2/search/image?query=아이린",
        headers=headers
    )

    if res2.status_code == 200:
        images = []
        docs = res2.json()
        for image in docs['documents']:
            images.append(image['image_url'])
    else:
        print("Error {0}".format(res2.status_code))
    return render_template(
        'images.html', images=images, nav_menu="image", kakao_oauth=kakao_oauth
    )

if __name__ == '__main__':
    # app.debug = True
    menus_blueprint.run(host="localhost", port="8080")