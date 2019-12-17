import json
import os
import pprint

from flask import Flask, render_template, session, request
from flask_restful import Api
import logging
import requests

from rest_client.example import example_blueprint
from rest_client.execute import execute_blueprint
from rest_server.rest_energy import EnergyDayResource, EnergyMonthResource, EnergyYearResource

application = Flask(__name__)
application.debug = True
application.config['SECRET_KEY'] = '12345678'
application.register_blueprint(execute_blueprint, url_prefix='/execute')
application.register_blueprint(example_blueprint, url_prefix='/example')

api = Api(application)
api.add_resource(EnergyDayResource, "/day-energy/<date>/area/<area>")
api.add_resource(EnergyMonthResource, "/month-energy/<date>/area/<area>")
api.add_resource(EnergyYearResource, "/year-energy/<date>/area/<area>")


@application.route('/manual')
def manual():
    return render_template(
        'manual.html',
        menu='manual'
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
    application.run(host="0.0.0.0", port="8080")
