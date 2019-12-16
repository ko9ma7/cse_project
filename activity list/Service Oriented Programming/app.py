import os
from flask import Flask, render_template, request
import logging

from database import base
from database.base import User

from views.menus import menus_blueprint
from views.auth import auth_blueprint, kakao_oauth

from rest_server.resource import TemperatureResource, TemperatureCreationResource, TemperatureByLocationResource
from rest_server.resource_check import resource_blueprint
from flask_restful import Api

from flask_login import current_user, LoginManager

application = Flask(__name__)

application.register_blueprint(menus_blueprint, url_prefix='/menus')
application.register_blueprint(auth_blueprint, url_prefix='/auth')
application.register_blueprint(resource_blueprint, url_prefix='/resource')

application.config['WTF_CSRF_SECRET_KEY'] = os.urandom(24)
application.config['SECRET_KEY'] = os.urandom(24)

login_manager = LoginManager()
login_manager.init_app(application)

@login_manager.user_loader
def load_user(user_id):
    q = base.db_session.query(User).filter(User.id == user_id)
    user = q.first()

    if user is not None:
        user._authenticated = True
    return user

api = Api(application)
api.add_resource(TemperatureResource, "/resource/<sensor_id>")
api.add_resource(TemperatureCreationResource, "/resource_creation")
api.add_resource(TemperatureByLocationResource, "/resource_location/<location>")

# # 파일로 남기기 위해  filename='test.log' parameter로 추가한다.
# logging.basicConfig(filename='test.log', level=logging.DEBUG)

# @application.route('/')
# def hello_world():
#     print("hello_world")
#     logging.info('root call!!!')
#     return '<h1>Hello World!!!!!</h1>'

@application.route('/')
def hello_html():
    return render_template(
        'index.html',
        nav_menu="home",
        current_user=current_user,
        kakao_oauth=kakao_oauth
    )

@application.route('/login', methods=['POST', 'GET'])
def success():
    if request.method == 'POST': # POST 형식은 'index.html'에 있는 'form'안의 'myName'을 가져오는 방식
        myName = request.form['myName']
    else: # GET 형식은 쿼리 스트링에서 ? 뒤에 나오는 값을 가져오는 방식
        myName = request.args.get('myName')

    return 'welcome {0} {0}'.format(myName)

@application.errorhandler(404)
def page_not_found(error):
    return "<h1>404 Error</h1>", 404

if __name__ == '__main__':
    logging.info('Flask Web Server Started!!')
    application.config['TEMPLATES_AUTO_RELOAD'] = True
    application.debug = True # application.config['DEBUG'] = True
    application.run(host="localhost", port="8080")