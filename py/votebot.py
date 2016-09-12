#!/usr/bin/env python

# http://polldaddy.com/vote.php?va=40&pt=0&r=1&p=2934942&a=13907341&o=&t=62580&token=36af0d9a1090f413f9e1d840eb599d35
# http://polldaddy.com/community/poll/2934942/  

# 9107801
# 41461942

# votebot.py by jiva

import urllib
import urllib2
import re
import time
import random
import sys
import commands

import sys

import requests

from BeautifulSoup import BeautifulSoup
import bs4
# or if you're using BeautifulSoup4:
# from bs4 import BeautifulSoup

proxies = []
port = {}

def printproxies():
	# http://www.crummy.com/software/BeautifulSoup/bs4/doc/
	bs = bs4.BeautifulSoup(urllib.urlopen('http://www.samair.ru/proxy/ip-address-01.htm'))
	table = bs.find_all('table', {'id': 'proxylist'})
	print table
	for row in table.find(True):
		print "--" + str(row)

def updateproxies():
	global proxies
	global port
	print 'updating proxies'
	proxies =[]
	#urllib.urlcleanup()
	for i in range(1,73):
		if i < 10:
			#fd = urllib.urlopen('http://www.samair.ru/proxy/proxy-0' + str(i) + '.htm')
			addy = 'http://www.samair.ru/proxy/proxy-0' + str(i) + '.htm'
			(status, output) = commands.getstatusoutput('curl -m 20 ' + addy)
		else:
			#fd = urllib.urlopen('http://www.samair.ru/proxy/proxy-' + str(i) + '.htm')
			addy = 'http://www.samair.ru/proxy/proxy-' + str(i) + '.htm'
			(status, output) = commands.getstatusoutput('curl -m 20 ' + addy)
		txt = output
		# get his gay-ass-port-naming scheme
		msx = re.findall(r'(\w=\d;+)', txt)
		if msx:
			port = {}
			for mx in msx:
				port[mx.split(";")[0].split("=")[0]] = mx.split(";")[0].split("=")[1]
		else:
			continue
		
		#get IPs, apply port rules, add to list
		msy = re.findall(r'(\d+\.\d+\.\d+\.\d+).+?write\(...(.+?)\)', txt)
		for (ip, enc) in msy:
			print ip + '\t' + enc
			enn = enc.split('+')
			portnum = ''
			for en in enn:
				if en != '':
					portnum = portnum + str(port[en])
			print ip + '\t' + portnum
			proxies.append(ip + ':' + str(portnum))
		time.sleep(1)
	urllib.urlcleanup()
	print 'num of proxies from up()' + str(len(proxies))
	vote()

cgood=0
cbad=0
def vote():
	#print 'num of proxies from vote()' + str(len(proxies))
	global proxies
	global cgood
	global cbad
	# for i in range(5):
		# for i in range(len(proxies)):
	for i in range(1):
		try:
			time.sleep(random.randint(1,2))			
			session = requests.Session()

			#fd = urllib2.urlopen('https://polldaddy.com/n/d4b3bc1131d4ce6c5c25fe6911e6a620/9107801?1444073691512')
			r = session.get('https://polldaddy.com/n/d4b3bc1131d4ce6c5c25fe6911e6a620/9107801?1444073691512')
			txt = r.text
			print "text=" + txt
			#(status, output) = commands.getstatusoutput('curl -x ' + proxies[i] + ' -m 20 http://polldaddy.com/community/poll/2934942/')
			#(status, output) = commands.getstatusoutput('curl -x -m 30 http://polldaddy.com/community/poll/2934942/')
			m = re.findall(r'PDV_n9107801=\'(.*?)\'', txt)
			if m:
				token = m[0]
				print 'tokeen=' + token
				votestring = 'http://polls.polldaddy.com/vote-js.php?p=9107801&b=2&a=41461942,&o=&va=0&cookie=1&n=' + str(token) + '&url=http%3A//www.yorkblog.com/varsity/2015/10/04/poll-yaiaa-girls-athlete-of-the-week-sept-21-sept-27-2/'
				#fd2 = urllib2.urlopen(votestring)
				print 'votestring=' + votestring
				#result = fd2.read()
				(status, output) = commands.getstatusoutput('curl -x ' + 'http://' + '162.208.49.45:3127' + ' -m 20 ' + votestring)
				print status
				print output
			else:
				f = open('out.txt','a')
				print 'Cant find vote()'
				f.write('Cant find vote()' + '\n')
				f.close()
			urllib.urlcleanup()
		except Exception,e:
			print str(e)
	#updateproxies()

def main():
	# RELEASE THE KRAKEN !!
	printproxies()
	# updateproxies()
	# vote()

if __name__ == "__main__":
	main()
