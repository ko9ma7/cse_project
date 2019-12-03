# -*- coding:utf-8 -*-

import sqlalchemy as sa
import sqlalchemy
from sqlalchemy import create_engine
from sqlalchemy import Column, Integer, String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from sqlalchemy_utils import JSONType


print(sqlalchemy.__version__)

engine = create_engine('sqlite:///customer2.db', echo=False, connect_args={'check_same_thread': False})
Base = declarative_base()

class Customer(Base):
    __tablename__ = 'customer'

    # 파이썬 객체 생성
    id = Column(Integer, primary_key=True)
    name = Column(String)
    address = Column(String)
    email = Column(String)
    age = Column(Integer)
    details = sa.Column(JSONType)


def print_all_customers(customers):
    for customer in customers:
        print_customer(customer)


def print_customer(customer):
    print("[ID: {0}] Name: {1} Address: {2}, Email: {3}, Age: {4}, Degree: {5}".format(
        customer.id,
        customer.name,
        customer.address,
        customer.email,
        customer.age,
        customer.details
    ))


# session은 마치 DB의 커서와 같은 역할
Base.metadata.create_all(engine)
db_session = sessionmaker(bind=engine)
db_session = db_session()


def main():
    count = db_session.query(Customer).count()
    print("### There are {0} rows in the table.".format(count))

    if count != 0:
        db_session.query(Customer).delete()
        count = db_session.query(Customer).count()
        print("### There are {0} rows in the table after performing 'delete'.".format(count))


    ## INSERT (POST)
    print("\n### db_session.add()")
    details = {
        'p_wind': [0.1, 0.2],
        'p_pv': 'car',
        'max-speed': '400 mph'
    }
    customer = Customer(name='김철수', address='서울 송파구', email='cskim@gmail.com', age=20, details=details)
    db_session.add(customer) # 객체(테이블)를 만들고 추가하기
    db_session.commit()

    count = db_session.query(Customer).count()
    print("### There are {0} rows in the table after performing 'add' and 'add_all'.".format(count))

if __name__ == "__main__":
    main()