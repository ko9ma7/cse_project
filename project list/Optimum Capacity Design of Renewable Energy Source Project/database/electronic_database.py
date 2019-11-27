# -*- coding:utf-8 -*-

import pytz
import sqlalchemy
from sqlalchemy import create_engine, and_, or_, Unicode, DateTime, Boolean
from sqlalchemy import Column, Integer, String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from datetime import datetime

print(sqlalchemy.__version__)

engine = create_engine(
    'sqlite:///electronic.db',
    echo=False,
    connect_args={'check_same_thread': False}
)
Base = declarative_base()


class Electronic(Base):
    __tablename__ = 'electronic'

    # 파이썬 객체 생성
    stn_id = Column(Integer, primary_key=True)   # 지점 번호
    stn_nm = Column(String)                      # 지점명


def print_all_electronic(electronics):
    for electronic in electronics:
        print_electronic(electronic)


def print_electronic(electronic):
    print("[STN_ID: {0}] STN_NM: {1}".format(
        electronic.stn_id,
        electronic.stn_nm,
    ))

# session은 마치 DB의 커서와 같은 역할
Base.metadata.create_all(engine)
db_session = sessionmaker(bind=engine)
db_session = db_session()

def main():
    count = db_session.query(Electronic).count()
    print("### There are {0} rows in the table.".format(count))

    if count != 0:
        db_session.query(Electronic).delete()
        count = db_session.query(Electronic).count()
        print("### There are {0} rows in the table after performing 'delete'.".format(count))


    # 지점번호(csv 파일)


    # ## INSERT (POST)
    # print("\n### db_session.add()")
    # weather = Weather(name='김철수', address='서울 송파구', email='cskim@gmail.com', age=20)
    # db_session.add(weather) # 객체(테이블)를 만들고 추가하기
    # db_session.commit()
    #
    # print("### db_session.add_all()")
    # db_session.add_all([
    #     Weather(name='이나라', address='대전 유성구', email='nrlee@gmail.com', age=21),
    #     Weather(name='나길동', address='대구 달서구', email='gdna@gmail.com', age=22),
    #     Weather(name='배칠수', address='인천 부평구', email='csbae@gmail.com', age=19)]
    # )
    # db_session.commit()
    #
    # count = db_session.query(Weather).count()
    # print("### There are {0} rows in the table after performing 'add' and 'add_all'.".format(count))
    #
    #
    # ## SELECT (GET)
    # print("\n### db_session.query(Customer).all()")
    # weathers = db_session.query(Weather).all() # 리스트 형태 반환
    # print_all_weathers(weathers)
    #
    # print("\n### db_session.query(Customer).filter(Customer.id == 2)")
    # weathers = db_session.query(Weather).filter(Weather.id == 2) # 리스트 형태 반환
    # print_all_weathers(weathers)
    #
    # print("\n### db_session.query(Customer).filter(Customer.id != 2)")
    # weathers = db_session.query(Weather).filter(Weather.id != 2)
    # print_all_weathers(weathers)
    #
    # print("\n### db_session.query(Customer).first()")
    # weather = db_session.query(Weather).first()
    # print_weather(weather)
    #
    # print("\n### db_session.query(Customer).filter(Customer.name.like('%수%'))")
    # weathers = db_session.query(Weather).filter(Weather.name.like('%수%'))
    # print_all_weathers(weathers)
    #
    # print("\n### db_session.query(Customer).filter(Customer.id.in_([2, 3]))")
    # weathers = db_session.query(Weather).filter(Weather.id.in_([2, 3]))
    # print_all_weathers(weathers)
    #
    # print("\n### and_")
    # weathers = db_session.query(Weather).filter(and_(Weather.id >= 3, Weather.name.like('%수%')))
    # print_all_weathers(weathers)
    #
    # print("\n### or_")
    # customers = db_session.query(Weather).filter(or_(Weather.id >= 3, Weather.name.like('%수%')))
    # print_all_weathers(customers)
    #
    # print("\n### db_session.query(Customer).get(3)")
    # weather = db_session.query(Weather).get(3) # primary key로 접근
    # print_weather(weather)
    #
    #
    # ## UPDATE (PUT)
    # weather.age = 25
    # db_session.commit()
    #
    # print("\n### db_session.query(Customer).get(3) after update")
    # weather = db_session.query(Weather).get(3)
    # print_weather(weather)
    #
    # # 특정 컬럼을 가져오지 않았기 때문에(get을 사용하지 않았기 때문에) 모든 컬럼의 내용이 바뀜
    # db_session.query(Weather).update({Weather.age: 23}, synchronize_session=False)
    # db_session.commit()
    #
    # print("\n### db_session.query(Customer).all() after bulk update")
    # weathers = db_session.query(Weather).all()
    # print_all_weathers(weathers)
    #
    #
    # ## DELETE (DELETE)
    # print("\n### delete the customer with id=3")
    # db_session.query(Weather).filter(Weather.id == 3).delete()
    # db_session.commit()
    #
    # print("\n### db_session.query(Customer).all() after deleting")
    # weathers = db_session.query(Weather).all()
    # print_all_weathers(weathers)


if __name__ == "__main__":
    main()