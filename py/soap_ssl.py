#!/usr/bin/python

### A script to call soap web service with client certificate.

# http://stackoverflow.com/questions/13688713/sslsocket-passphrase-password-in-python
# change pfx (windows, binary) to pem file.
# openssl pkcs12 -in file.pfx -out file.pem

import requests
import logging
from BeautifulSoup import BeautifulSoup

_HTTP_HEADERS={"Content-Type" : "text/xml; charset=utf-8",
		"SOAPAction" : "https://www.cnet.jnet.beta.pa.gov/cnetweb/GetSecurityProfile"}
# read soap request from file.
_XML = open('request.xml').read()

# initialize logging
logging.basicConfig(level=logging.DEBUG)
# create a http session
session = requests.Session()
out = session.post("https://www.snet.jnet.beta.pa.gov/cnetweb/cnetwebservice.asmx?WSDL",
	data=_XML,
	cert=('/Users/Dhval/Documents/cpin.pem'), verify=False, headers=_HTTP_HEADERS)
# if we have a javascript redirect
if BeautifulSoup('script') is not None:
	print out.text
	print out.headers
	out = session.post("https://www.snet.jnet.beta.pa.gov/cnetweb/cnetwebservice.asmx?WSDL",
		data=_XML,
	cert=('/Users/Dhval/Documents/cpin.pem'), verify=False, headers=_HTTP_HEADERS)
print "***** RESPONSE *****"
print out.text