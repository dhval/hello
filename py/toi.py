#!/usr/bin/python

import sys
import re
import requests
import urllib
import urllib2
import urllib
from commands import *
from BeautifulSoup import BeautifulSoup
# or if you're using BeautifulSoup4:
# from bs4 import BeautifulSoup

TOI_URL = 'http://timesofindia.indiatimes.com/commentsdata.cms?curpg=1&pagenum=1&size=1000&pcode=TOI&appkey=TOI&sortcriteria=AgreeCount&msid='

def downloadJSON(id):
	file_name = '/Users/dhval/toi_json/' + id + '.json'
	r = requests.get(TOI_URL + id, stream=True)
	print file_name + ' ' + r.text
	if r.status_code == requests.codes.ok and r.text != '[]' and r.text != '':
		with open(file_name, 'wb') as f:
			for chunk in r.iter_content(chunk_size=1024): 
				if chunk: # filter out keep-alive new chunks
					f.write(chunk)
					f.flush()		

if __name__ == "__main__":
	for i in range(26645503, 26645903):
		downloadJSON(str(i))