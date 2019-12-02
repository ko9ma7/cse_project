# -*- coding:utf-8 -*-

import sys
import sqlalchemy
import pandas as pd
from sqlalchemy import create_engine, exc
from sqlalchemy import Column, Integer, String
from sqlalchemy.dialects.postgresql import ARRAY
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.dialects import postgresql
from sqlalchemy.orm import sessionmaker
sys.path.insert(0, '../rest_server')
from read_weather import read_weather


print(sqlalchemy.__version__)
engine = create_engine(
    'postgresql:///weather.db',
    # 'postgresql://postgres:battlesun99@localhost:5432/postgres',
    echo=True
)

# engine = create_engine(
#     'sqlite:///weather.db',
#     # 'postgresql://postgres:postgres@localhost:5432/postgres',
#     # 'postgresql:///weather.db',
#     echo=False,
#     connect_args={'check_same_thread': False}
# )

Base = declarative_base()

class Weather(Base):
    __tablename__ = 'weather'

    # 파이썬 객체 생성
    stn_id = Column(Integer, primary_key=True)                 # 지점 번호
    stn_nm = Column(String)                                    # 지점명
    tm = Column(String)                                        # 관측시간
    icsr = Column("icsr", postgresql.ARRAY(sqlalchemy.FLOAT))  # 일사량
    ws = Column("ws", postgresql.ARRAY(sqlalchemy.FLOAT))      # 풍속

def print_all_weathers(weathers):
    for weather in weathers:
        print_weather(weather)


def print_weather(weather):
    print("[STN_ID: {0}] STN_NM: {1} TM: {2}, ICSR: {3}, WS: {4}".format(
        weather.stn_id,
        weather.stn_nm,
        weather.tm,
        weather.icsr,
        weather.ws
    ))

# session은 마치 DB의 커서와 같은 역할
Base.metadata.create_all(engine)
db_session = sessionmaker(bind=engine)
db_session = db_session()

def main():
    count = db_session.query(Weather).count()
    print("### There are {0} rows in the table.".format(count))

    stnIds = 177
    date = '20170601'
    stnNm, photovoltaic_data, wind_data = read_weather(stnIds, date, date)

    print(stnIds)
    print(stnNm)
    print(date)
    print(photovoltaic_data)
    print(wind_data)

    # weather = pd.DataFrame({
    #     'stn_id': stnIds,
    #     'stn_nm': stnNm,
    #     'tm': date,
    #     'icsr': photovoltaic_data,
    #     'ws': wind_data},
    #
    #     columns=['stn_id', 'stn_nm', 'tm', 'icsr', 'ws'])
    #
    # engine.execute("DROP TABLE IF EXISTS public.weather;")
    #
    # weather.to_sql(name='weather',
    #                con=engine,
    #                schema='public',
    #                if_exists='fail',  # {'fail', 'replace', 'append'), default 'fail'
    #                index=True,
    #                index_label='id',
    #                chunksize=2,
    #                dtype={
    #                    'stn_id': sqlalchemy.types.INTEGER(),
    #                    'stn_nm': sqlalchemy.types.VARCHAR(100),
    #                    'tm': sqlalchemy.DateTime(),
    #                    'icsr': sqlalchemy.types.Float(precision=3),
    #                    'ws': sqlalchemy.types.Float(precision=3)
    #                })

    # # INSERT (POST)
    # print("\n### db_session.add()")
    # weather = Weather(stn_id=stnIds, stn_nm=stnNm, tm=date, icsr=photovoltaic_data, ws=wind_data)
    # db_session.add(weather) # 객체(테이블)를 만들고 추가하기
    # db_session.commit()
    #
    # if count != 0:
    #     db_session.query(Weather).delete()
    #     count = db_session.query(Weather).count()
    #     print("### There are {0} rows in the table after performing 'delete'.".format(count))
    #
    # weather = Weather(stn_id=stnIds, stn_nm=stnNm, tm=date, icsr=photovoltaic_data, ws=wind_data)
    # db_session.add(weather)  # 객체(테이블)를 만들고 추가하기
    # db_session.commit()
    #
    # print("### There are {0} rows in the table after performing 'add'.".format(count))


if __name__ == "__main__":
    main()