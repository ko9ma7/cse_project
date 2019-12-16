import sqlite3
import time

from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String,  create_engine
from sqlalchemy.orm import scoped_session, sessionmaker

insertion_resource = """
    INSERT INTO customer VALUES (?, ?, ?);
"""
Base = declarative_base()
DBSession = scoped_session(sessionmaker())

class Customer(Base):
    __tablename__ = "customer"
    id = Column(Integer, primary_key=True)
    name = Column(String(255))

def init_sqlite3(dbname):
    conn = sqlite3.connect(dbname)
    c = conn.cursor()
    # c.execute("DROP TABLE IF EXISTS customer")
    # c.execute("CREATE TABLE customer (id INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY(id))")
    conn.commit()
    return conn

def test_sqlite3(n=10, dbname = 'sqlite3.db'):
    conn = init_sqlite3(dbname)
    c = conn.cursor()
    t0 = time.time()
    for i in range(n):
        row = ('NAME ' + str(i + 11),)
        c.execute("INSERT INTO customer (name) VALUES (?)", row)
    conn.commit()
    print("sqlite3: Total time for " + str(n) + " records " + str(time.time() - t0) + " sec")

if __name__ == '__main__':
    test_sqlite3(10)