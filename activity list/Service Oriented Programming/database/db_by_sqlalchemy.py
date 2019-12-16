import time
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String,  create_engine
from sqlalchemy.orm import scoped_session, sessionmaker

insertion_resource = """
    INSERT INTO TEMPERATURE VALUES (null, ?, ?, ?, ?);
"""

Base = declarative_base()
DBSession = scoped_session(sessionmaker())

class Customer(Base):
    __tablename__ = "customer"
    id = Column(Integer, primary_key=True)
    name = Column(String(255))

def init_sqlalchemy(dbname = 'sqlite:///sqlalchemy4.db'):
    engine  = create_engine(dbname, echo=False)
    DBSession.configure(bind=engine, autoflush=False, expire_on_commit=False)
    # Base.metadata.drop_all(engine)
    # Base.metadata.create_all(engine)
    return DBSession

def test_sqlalchemy(n=10):
    init_sqlalchemy()
    t0 = time.time()
    for i in range(n):
        customer = Customer()
        customer.name = 'NAME ' + str(i)
        DBSession.add(customer)
    DBSession.commit()
    print("SqlAlchemy: Total time for " + str(n) + " records " + str(time.time() - t0) + " secs")

if __name__ == '__main__':
    test_sqlalchemy(10)