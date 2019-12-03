import sqlalchemy as sa
import sqlalchemy
from sqlalchemy import create_engine
from sqlalchemy import Column, Integer, String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from sqlalchemy_utils import JSONType

# # sqlite 서버 구축
# engine = create_engine('sqlite:///renewable_energy.db', echo=False, connect_args={'check_same_thread': False})
# Base = declarative_base()
#
# # session은 마치 DB의 커서와 같은 역할
# Base.metadata.create_all(engine)
# db_session = sessionmaker(bind=engine)
# db_session = db_session()

# class Energy(Base):
#     __tablename__ = 'energy'
#
#     # 파이썬 객체 생성
#     id = Column(Integer, primary_key=True)
#     Administrative_Area = Column(String)
#     Observation = Column(String)
#     energy = Column(JSONType)
from tabulate import tabulate


def main():
    # count = db_session.query(Energy).count()
    # print("### There are {0} rows in the table.".format(count))
    #
    # if count != 0:
    #     db_session.query(Energy).delete()
    #     count = db_session.query(Energy).count()
    #     print("### There are {0} rows in the table after performing 'delete'.".format(count))

    # 재생에너지 데이터 확보
    weather_df = return_weather_data()
    electronic_df = return_electronic_data()
    weather_df['P_load'] = electronic_df['P_load']
    renewable_energy = weather_df
    print(tabulate(renewable_energy, headers='keys', tablefmt='psql'))
    # print(renewable_energy)

    # INSERT (POST)
    print("\n### db_session.add()")
    for idx in range(len(renewable_energy)):
        energy_data = {
            'P_pv': renewable_energy['P_pv'][idx],
            'P_wind': renewable_energy['P_wind'][idx],
            'P_load': renewable_energy['P_load'][idx]
        }

        print(energy_data)

    #     energy = Energy(
    #         Administrative_Area=renewable_energy['Administrative_Area'][idx],
    #         Observation=renewable_energy['Observation'][idx],
    #         energy=energy_data
    #     )
    #     db_session.add(energy) # 객체(테이블)를 만들고 추가하기
    #
    # db_session.commit()
    #
    # count = db_session.query(Energy).count()
    # print("### There are {0} rows in the table after performing 'add' and 'add_all'.".format(count))