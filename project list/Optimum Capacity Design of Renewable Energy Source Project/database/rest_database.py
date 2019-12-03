# -*- coding:utf-8 -*-

from electronic_database import return_electronic_data
from weather_database import return_weather_data
from tabulate import tabulate

def get_Energy():
    weather_df = return_weather_data()
    electronic_df = return_electronic_data()
    weather_df['P_load'] = electronic_df['P_load']
    renewable_energy = weather_df

    return renewable_energy