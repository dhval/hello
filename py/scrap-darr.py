import re
import requests

from BeautifulSoup import BeautifulSoup, SoupStrainer 
import httplib2

def findUrlDarr(url):
#	print url
	http = httplib2.Http()
	status, response = http.request(url)

	for link in BeautifulSoup(response, parseOnlyThese=SoupStrainer('a')):
		if link.has_key('href'):
			match = re.search(r'idowatch', link['href'])
			if match:
				match2 = re.findall(r'=(.*)$', link['href'])
				if match2:
				 print "youtube-dl http://idowatch.net/" + match2[0] + " -o \"" + link.text + ".mp4\""

def findUrlCid(url):
#	print url
	http = httplib2.Http()
	status, response = http.request(url)

	for link in BeautifulSoup(response, parseOnlyThese=SoupStrainer('a')):
		if link.has_key('href'):
			match = re.search(r'http://www.tellysony.com/sony-tv/\?si=', link['href'])
			if match:
#				print link['href']
				match2 = re.findall(r'=(.*)$', link['href'])
				if match2:
					match3 = re.findall(r'(?i)Part', link.text)
					if not match3:
						print "youtube-dl http://watchvideo2.us/" + match2[0] + ".html -o \"" + link.text + ".mp4\""


http = httplib2.Http()
# "http://www.desirulez.me/forums/117-CID/page3"
# http://www.desirulez.me/forums/3665-Darr-Sabko-Lagta-Hai
status, response = http.request("http://www.desirulez.me/forums/3665-Darr-Sabko-Lagta-Hai")
for link in BeautifulSoup(response, parseOnlyThese=SoupStrainer('a')):
	if (link.has_key('href') and link.has_key('class') and link['class'] == 'title'):
#   All links with watch-online in href
		match = re.search(r'Watch-Online', link['href'])
		if match:
			findUrlDarr('http://www.desirulez.me/' + link['href'])
