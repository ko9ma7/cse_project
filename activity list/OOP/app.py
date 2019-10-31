from flask import Flask, render_template, request
import logging
from news import news_blueprint
from rest_server.resource import TemperatureResource, TemperatureCreationResource, TemperatureByLocationResource
from rest_server.resource_check import resource_blueprint
from flask_restful import Api

application = Flask(__name__)
application.register_blueprint(news_blueprint, url_prefix='/news')
application.register_blueprint(resource_blueprint, url_prefix='/resource')

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
    value = 30
    value_list = ['파이썬', '자바', '스위프트']
    return render_template(
        'index.html',
        name='JMH',
        data=value,
        value_list=value_list,
        values_list_0=value_list[0],
        values_list_1=value_list[1],
        values_list_2=value_list[2]
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