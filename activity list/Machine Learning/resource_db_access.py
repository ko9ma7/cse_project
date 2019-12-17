# # load_ext watermark
# # watermark -v -p numpy,scipy,sklearn,pandas,matplotlib
# # 파이썬 2와 파이썬 3 지원
# from __future__ import division, print_function, unicode_literals
#
# # 공통
# from pprint import pprint
#
# import numpy as np
# import os
#
# # 일관된 출력을 위해 유사난수 초기화
# from tabulate import tabulate
#
# np.random.seed(42)
#
# # 맷플롯립 설정
# matplotlib inline
from pprint import pprint

import matplotlib
import matplotlib.pyplot as plt
#
# plt.rcParams['axes.labelsize'] = 14
# plt.rcParams['xtick.labelsize'] = 12
# plt.rcParams['ytick.labelsize'] = 12
#
# # 한글출력
# matplotlib.rc('font', family='AppleGothic')
# plt.rcParams['axes.unicode_minus'] = False
# import sqlite3
#
# selection_resource_by_administrative_area = """
#     SELECT * FROM Renewable_Energy_By_Day WHERE Administrative_Area=?
# """
#
# class RenewableEnergySourceDatabase:
#     def __init__(self):
#         self.conn = sqlite3.connect('renewable_energy_database.db')
#
#     def readByAdministrativeArea(self, Administrative_Area):
#         cur = self.conn.cursor()
#         cur.execute(
#             selection_resource_by_administrative_area, (Administrative_Area,)
#         )
#         row = cur.fetchall()
#
#         return row
#
# resd = RenewableEnergySourceDatabase()
# data = resd.readByAdministrativeArea('경기도')
#
import numpy as np
import pandas as pd
import hvplot.pandas
#
# df = pd.DataFrame(data)
#
# date = df[2]
# tidx = pd.date_range("2017-01-01", "2018-12-31", freq="D")
# p_wind = df[3]
# print(date)
# print(p_wind)
#
# x = [x for x in range(24)]
# y = p_wind[0]
# print(x)
#
# d = { 'time' : x, 'p_wind' : y }
# iris = pd.DataFrame(d)
# print(iris)
# # This will pick a number of normally distributed random numbers
# # where the number is specified by periods
# # data = np.random.randn(periods)
#
# # data = np.array([y, x])
# # # plt.scatter(data[:, 0], data[:, 1])
# # plt.title("Time / Distance")
# # plt.xlabel("Delivery Distance (meter)")
# # plt.ylabel("Time Consumed (minute)")
# # plt.axis([0, 24, 0, 1])
# # plt.show()
import seaborn as sns
# import sm as sm
import statsmodels.api as sm
from sklearn.linear_model import LinearRegression

from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String, FLOAT, create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy_utils import JSONType

Base = declarative_base()
DBSession = scoped_session(sessionmaker())

class Energy(Base):
    __tablename__ = "Renewable_Energy_By_Day"
    # 파이썬 객체 생성
    id = Column(Integer, primary_key=True)
    Administrative_Area = Column(String)
    Observation = Column(String)

    P_pv = Column(JSONType)
    P_wind = Column(JSONType)
    P_load = Column(JSONType)

    Optimal_prompt1 = Column(FLOAT)
    Optimal_prompt2 = Column(FLOAT)
    Optimal_cost = Column(FLOAT)

    Optimal_p_wind = Column(JSONType)
    Optimal_p_dummy = Column(JSONType)
    Optimal_p_pv = Column(JSONType)
    Optimal_p_battery = Column(JSONType)
    Optimal_p_load = Column(JSONType)
    Optimal_soc = Column(JSONType)
    Optimal_p_dg = Column(JSONType)
    Optimal_lolp = Column(JSONType)

def init_sqlalchemy(dbname = 'sqlite:///renewable_energy_database.db'):
    engine  = create_engine(dbname, echo=False)
    DBSession.configure(bind=engine, autoflush=False, expire_on_commit=False)
    # Base.metadata.drop_all(engine)
    # Base.metadata.create_all(engine)
    return DBSession


def print_all_customers(energys):
    for energy in energys:
        print_customer(energy)

def print_customer(energy):
    print("[ID: {0}] Administrative_Area: {1} Observation: {2} P_wind: {3}".format(
        energy.id,
        energy.Administrative_Area,
        energy.Observation,
        energy.P_wind,
        energy.P_pv,
        energy.P_load
    ))

def make_nonlinear(seed=0):
    np.random.seed(seed)
    n_samples = 30
    X = np.sort(np.random.rand(n_samples))
    y = np.sin(2 * np.pi * X) + np.random.randn(n_samples) * 0.1
    X = X[:, np.newaxis]
    return (X, y)

def polyreg(degree, seed=0, ax=None):
    X, y = make_nonlinear(seed)

    dfX = pd.DataFrame(X, columns=["x"])
    dfX = sm.add_constant(dfX)
    dfy = pd.DataFrame(y, columns=["y"])
    df = pd.concat([dfX, dfy], axis=1)

    model_str = "y ~ "
    for i in range(degree):
        if i == 0:
            prefix = ""
        else:
            prefix = " + "
        model_str += prefix + "I(x**{})".format(i + 1)
    model = sm.OLS.from_formula(model_str, data=df)
    result = model.fit()

    if ax:
        ax.scatter(X, y)
        xx = np.linspace(0, 1, 1000)
        dfX_new = pd.DataFrame(xx[:, np.newaxis], columns=["x"])
        ax.plot(xx, result.predict(dfX_new))
        ax.set_ylim(-2, 2)
        ax.set_title("차수={}, 시드값={}".format(degree, seed))
        xlabel = "\n".join(str(result.params).split("\n")[:-1])
        font = {'family': 'NanumGothicCoding', 'color':  'black', 'size': 10}
        ax.set_xlabel(xlabel, fontdict=font)

    return result

def main():

    # 재생에너지 데이터 확보
    DBSession = init_sqlalchemy()

    energys = DBSession.query(Energy).all()

    # 전국의 데이터
    total_wind_data = []
    total_pv_data = []
    total_load_data = []

    for energy in energys:
        total_wind_data.append(energy.P_wind)
        total_pv_data.append(energy.P_pv)
        total_load_data.append(energy.P_load)

    #########################################
    # P_wind data
    wind_data = {}
    id = [x for x in range(24)]
    wind_data['id'] = id

    for i in range(11680):
        name = 'p_wind_' + str(i)
        wind_data[name] = total_wind_data[i]

    #########################################
    # P_pv data
    pv_data = {}
    id = [x for x in range(24)]
    pv_data['id'] = id

    for i in range(11680):
        name = 'p_pv_' + str(i)
        pv_data[name] = total_pv_data[i]

    #########################################
    # P_load data
    load_data = {}
    id = [x for x in range(24)]
    load_data['id'] = id

    for i in range(11680):
        name = 'p_load_' + str(i)
        load_data[name] = total_load_data[i]






    # # 0 ~ 730 경기도
    # for i in range(730):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 730 ~ 1460 강원도
    # for i in range(730, 1460):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 1460 ~ 2190 경상남도
    # for i in range(1460, 2190):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 2190 ~ 2920 경상북도
    # for i in range(2190, 2920):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 2920 ~ 3650 전라남도
    # for i in range(2920, 3650):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 3650 ~ 4380‬ 전라북도
    # for i in range(3650, 4380):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 4380‬ ~ 5110 충청남도
    # for i in range(4380, 5110):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 5110 ~ 5840 충청북도
    # for i in range(5110, 5840):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 5840 ~ 6570 제주도
    # for i in range(5840, 6570):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 6570 ~ 7300 서울특별시
    # for i in range(6570, 7300):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 7300 ~ 8030 인천광역시
    # for i in range(7300, 8030):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 8030 ~ 8760 전라남도
    # for i in range(8030, 8760):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 8760 ~ 9490 대전광역시
    # for i in range(8760, 9490):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 9490 ~ 10220 광주광역시
    # for i in range(9490, 10220):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 10220 ~ 10950 울산광역시
    # for i in range(10220, 10950):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()
    #
    # # 10950 ~ 11680 부산광역시
    # for i in range(10950, 11680):
    #     name = 'p_wind_' + str(i)
    #     plt.scatter(wind_data['id'], wind_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 20])
    # plt.show()





    # # 0 ~ 730 경기도
    # for i in range(730):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 730 ~ 1460 강원도
    # for i in range(730, 1460):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 1460 ~ 2190 경상남도
    # for i in range(1460, 2190):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 2190 ~ 2920 경상북도
    # for i in range(2190, 2920):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 2920 ~ 3650 전라남도
    # for i in range(2920, 3650):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 3650 ~ 4380‬ 전라북도
    # for i in range(3650, 4380):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 4380‬ ~ 5110 충청남도
    # for i in range(4380, 5110):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 5110 ~ 5840 충청북도
    # for i in range(5110, 5840):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 5840 ~ 6570 제주도
    # for i in range(5840, 6570):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 6570 ~ 7300 서울특별시
    # for i in range(6570, 7300):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 7300 ~ 8030 인천광역시
    # for i in range(7300, 8030):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 8030 ~ 8760 전라남도
    # for i in range(8030, 8760):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 8760 ~ 9490 대전광역시
    # for i in range(8760, 9490):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 9490 ~ 10220 광주광역시
    # for i in range(9490, 10220):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 10220 ~ 10950 울산광역시
    # for i in range(10220, 10950):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()
    #
    # # 10950 ~ 11680 부산광역시
    # for i in range(10950, 11680):
    #     name = 'p_pv_' + str(i)
    #     plt.scatter(pv_data['id'], pv_data[name])
    #     plt.plot()
    #
    # plt.axis([-1, 24, 0, 6])
    # plt.show()






    # 0 ~ 730 경기도
    for i in range(730):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 2])
    plt.show()

    # 730 ~ 1460 강원도
    for i in range(730, 1460):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 1])
    plt.show()

    # 1460 ~ 2190 경상남도
    for i in range(1460, 2190):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 1])
    plt.show()

    # 2190 ~ 2920 경상북도
    for i in range(2190, 2920):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 1.5])
    plt.show()

    # 2920 ~ 3650 전라남도
    for i in range(2920, 3650):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 1])
    plt.show()

    # 3650 ~ 4380‬ 전라북도
    for i in range(3650, 4380):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 0.3])
    plt.show()

    # 4380‬ ~ 5110 충청남도
    for i in range(4380, 5110):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 2])
    plt.show()

    # 5110 ~ 5840 충청북도
    for i in range(5110, 5840):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 0.1])
    plt.show()

    # 5840 ~ 6570 제주도
    for i in range(5840, 6570):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 0.1])
    plt.show()

    # 6570 ~ 7300 서울특별시
    for i in range(6570, 7300):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 0.05])
    plt.show()

    # 7300 ~ 8030 인천광역시
    for i in range(7300, 8030):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 1.5])
    plt.show()

    # 8030 ~ 8760 전라남도
    for i in range(8030, 8760):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 0.05])
    plt.show()

    # 8760 ~ 9490 대전광역시
    for i in range(8760, 9490):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 0.05])
    plt.show()

    # 9490 ~ 10220 광주광역시
    for i in range(9490, 10220):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 0.1])
    plt.show()

    # 10220 ~ 10950 울산광역시
    for i in range(10220, 10950):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 0.6])
    plt.show()

    # 10950 ~ 11680 부산광역시
    for i in range(10950, 11680):
        name = 'p_load_' + str(i)
        plt.scatter(load_data['id'], load_data[name])
        plt.plot()

    plt.axis([-1, 24, 0, 1])
    plt.show()

main()