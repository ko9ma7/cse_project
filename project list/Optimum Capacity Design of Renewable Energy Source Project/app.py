import json
import os
import pprint

from flask import Flask, render_template, session, request
from flask_restful import Api
import logging
import requests

import searchform
from rest_server.resource import TemperatureResource, TemperatureCreationResource, TemperatureByLocationResource

application = Flask(__name__)
application.debug = True
application.config['SECRET_KEY'] = '12345678'
api = Api(application)
api.add_resource(TemperatureResource, "/resource/<sensor_id>")
api.add_resource(TemperatureCreationResource, "/resource/creation")
api.add_resource(TemperatureByLocationResource, "/resource/location/<location>")


@application.route('/manual')
def manual():
    return render_template(
        'manual.html',
        menu='manual'
    )


@application.route('/execute_ratio', methods=['GET', 'POST'])
def execute_ratio():
    form = searchform()
    if request.method == 'GET':
        query = request.args.get('query')
        if query != None :
            # REST GET Method
            # 자원을 가져오는 url
            # ex) http://localhost:8080/resource/t2
            url = 'http://127.0.0.1:8080/resource/'+query
            res = requests.get(
                url=url
            )
            return render_template(
                'execute_ratio.html',
                res=res.json(),
                menu='execute',
                sub_menu='ratio',
                form=form
            )
        else :
            url = 'http://127.0.0.1:8080/resource/'
            return render_template(
                'execute_ratio.html',
                form=form,
                menu='execute',
                sub_menu='ratio',
                res=''
            )


@application.route('/execute_load')
def execute_load():
    form = searchform()
    res=None
    if request.method == 'GET':
        if form.validate_on_submit():
            query = request.form['query']
            # url = 'http://127.0.0.1:8080/resource/'+query
            url = 'http://127.0.0.1:8080/resource/t1'

            res = requests.get(
                url=url
            )
            return render_template(
                'execute_load.html',
                res=res.json(),
                form=form
            )
    url = 'http://127.0.0.1:8080/resource/t1'
    res = requests.get(
        url=url
    )
    return render_template(
        'execute_load.html',
        form=form,
        menu='execute',
        sub_menu='load',
        res=res.json()
    )


@application.route('/example', methods=['GET', 'POST'])
def example():
    series = [10, 20, 30, 10, 20, 30, 40]
    form = searchform()

    if request.method == 'POST':
        if form.validate_on_submit():
            query = form.data['query']
            url = 'http://127.0.0.1:8080/resource/'+query
            res = requests.get(
                url=url
            )
        return render_template(
            'example.html',
            menu='example',
            series=series,
            res=res.json(),
            form=form
        )

    url = 'http://127.0.0.1:8080/resource/t2'
    res = requests.get(
        url=url
    )
    pprint.pprint(res.json())
    return render_template(
        'example.html',
        menu='example',
        series=series,
        res=res.json(),
        form=form
    )


@application.route('/')
def hello_html():
    return render_template(
        'index.html',
        menu='main',
    )

if __name__ == "__main__":
    logging.info("Flask Web Server Started!!!")

    application.debug = True
    application.config['DEBUG'] = True
    application.run(host="localhost", port="8080")