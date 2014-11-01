#!/usr/bin/python

###
# Refereneces :
# 1. http://pymotw.com/2/sqlite3/
# 2. https://docs.python.org/3/library/sqlite3.html
###

import sqlite3
from contextlib import contextmanager

DEF_DB_PATH="//Users/Dhval/sqlite.db"

@contextmanager
def sqlite_db(path=DEF_DB_PATH):
	conn = None
	try:
		con =  sqlite3.connect(path)
		yield con
	finally:
		if con is not None:
			con.close()

def check_table_exists(table_name):
	with sqlite_db() as conn:
		query = '''SELECT name FROM sqlite_master WHERE type='table' AND
		 name= :table_name;'''
		cursor = conn.execute(query, {'table_name':table_name})
		for r in cursor:
			print r

def create_table():
	with sqlite_db("//Users/Dhval/sqlite.db") as conn:
		conn.execute('''CREATE TABLE if not exists COMPANY
       (ID INT PRIMARY KEY     NOT NULL,
       NAME           TEXT    NOT NULL,
       AGE            INT     NOT NULL,
       ADDRESS        CHAR(50),
       SALARY         REAL);''')
		print "Table created successfully";

if __name__ == "__main__":
	create_table()
	check_table_exists("COMPANY")