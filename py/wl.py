#!/usr/bin/python

import sys
import os
from optparse import OptionParser

# wls:/JNET_Domain/config/Applications
# wls:/JNET_Domain/config/Clusters
# wls:/JNET_Domain/config/JDBCConnectionPools
# wls:/JNET_Domain/config/JDBCTxDataSources
# wls:/JNET_Domain/config/Servers
# wls:/JNET_Domain/config/Targets

# wls:/JNET_Domain/config/Servers
# wls:/JNET_Domain/config/Targets
# wls:/JNET_Domain/config/Servers
# wls:/JNET_Domain/config/Targets
# wls:/JNET_Domain/config/Servers
# wls:/JNET_Domain/config/Targets

# svrAttrList = ls('Targets', returnMap='true', returnType='c')

# config()

# ls ('../../JDBCConnectionPools/CountyDOCDataSource') 

# connect('wlsadmin', 'Weblogic12c','t3://10.1.26.51:7001')

# ls('JDBCConnectionPools/AppEnhancements', returnType='a', returnMap='true')

# ls('JDBCConnectionPools/AppEnhancements', returnType='a', returnMap='true')
# ls('JDBCTxDataSources/AppEnhancements', returnType='a', returnMap='true')
# ls('Applications/applications', returnType='a', returnMap='true')



def conn(environ):
	try:
		connect('wlsadmin','Weblogic12c',environ)
	except ConnectionException,e:
		print '\033 [1;31m \033'

def getDataSource(name='JDBCConnectionPools'):
	return ls(name, returnMap='true')

def getDataSourceInfo(src, name='JDBCConnectionPools'):
	return ls(name + '/' + src, returnType='a', returnMap='true')


def visitJ(name='JDBCConnectionPools'):
	nodes = ls(name, returnMap='true')
	cd(name) 

	for node in nodes:
		source = ls(node, returnType='a', returnMap='true')
		print "current node =" + node + delim
		print "%s, %s, %s, %s" %(source['Name'], source['Properties'], source['URL'], source['Targets'])


delim = "#$#"
environ = "t3://10.1.26.51:7001"
conn(environ)

# You can also optionally specify whether you want WLST output to be sent to stdout; the toStdOut argument defaults to true. 
redirect('wlst.log', 'false')
config()

dataSources = getDataSource()
for src in dataSources:
	srcInfo = getDataSourceInfo(src)
	dir(srcInfo)
	print srcInfo['Name']
	print srcInfo




disconnect()