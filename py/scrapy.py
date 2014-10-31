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

_HTTP_HEADERS={"X-Requested-With" : "XMLHttpRequest", "User-Agent" : "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36"}
_LOGIN = 'vkpach.namtest'
_CAPTCHA_URL = 'https://www.inet.jnet.beta.pa.gov/pwmgt2/captcha?v=1406342497800'
_STEP1_URL = 'https://www.inet.jnet.beta.pa.gov/pwmgt2?step=1'
_STEP2_URL = 'https://www.inet.jnet.beta.pa.gov/pwmgt2?step=2'
_STEP1_DATA = { 'username' : 'LOGIN', 'captcha' : '00000', 'fragments' : 'wrapped-content', 'ajaxSource' : 'true' }
_STEP2_DATA = { 'firstAnswer' : '0000', 'secondAnswer' : '0000', 'fragments' : 'wrapped-content', 'ajaxSource' : 'true' }

# Read logins from file
def readData(fName):
	users = []
	with open (fName, 'r') as f:
		for line in f:
			users.append(line.strip().replace('"', ''))
	return users

# Use existing session to download captcha image
def generateCaptha(session, tries=3):
	if tries <= 0:
		return None
	captchaCode = None
	r = session.get(_CAPTCHA_URL, stream=True)
	if r.status_code == 200:
		with open("captcha.jpg", 'wb') as f:
			for chunk in r.iter_content():
				f.write(chunk)
		# decode captcha and store in a out.txt
		status, text = getstatusoutput("tesseract captcha.jpg out")
		# read decoded captcha from file
		status, text = getstatusoutput("cat out.txt")
		captchaCode = text.strip()
	match = re.match("[\w]{6}", captchaCode)
	if captchaCode is None or match is None:
		generateCaptha(session, tries-1)
	else:
		return captchaCode

def forgotPwdStep1(session, login, captchaCode):
	_STEP1_DATA['username'] = login
	_STEP1_DATA['captcha'] = captchaCode
	print 'Preparing Step 1 ... ' + str(_STEP1_DATA)
	response = session.post(_STEP1_URL, params=_STEP1_DATA)
	print response.headers
	if response.status_code == requests.codes.ok and 'spring-redirect-url' in response.headers:
		return ("success", response)
	else:
		return (None, response)

def userExists(user):
	session = requests.Session()
	captchaCode = generateCaptha(session)
	(status, response) = forgotPwdStep1(session, user, captchaCode)
	if status == "success":
		print "success"
	else:
		print response.text

def makeCall(user):
	session = requests.Session()
	captchaCode = generateCaptha(session)
	(status, response) = forgotPwdStep1(session, user, captchaCode)
	if status == 'success':
		print 'Preparing Step 2 ... ' + str(_STEP2_DATA)
		response = session.post(_STEP2_URL, params=_STEP2_DATA, headers=_HTTP_HEADERS)
		soup = BeautifulSoup(response.text)
		print soup('script')
		print soup('div', {'id': 'wrapper'})

if __name__ == "__main__":
	users = readData("users.txt")
	for user in users:
		print makeCall(user)
	# makeCall()
