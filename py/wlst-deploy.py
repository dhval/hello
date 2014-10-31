#!/usr/bin/python

#==================================
# http://docs.oracle.com/cd/E11035_01/wls100/config_scripting/reference.html#wp1003449
# http://wlstbyexamples.blogspot.in/2010/09/deployment-script-using-wlst.html#.U8gQZp5dUeQ
# http://weblogicserveradministration.blogspot.com/2014/03/weblogic-wlst-weblogic-scripting-tool.html?utm_source=BP_recent
#==================================

import sys
import os
from optparse import OptionParser

def conn(environ):
	try:
		connect('wlsadmin',os.environ.get('WL_PWD'),environ)
	except ConnectionException,e:
		print '\033 [1;31m \033'

def appStatus(appName):
	cd('domainRuntime:/AppRuntimeStateRuntime/AppRuntimeStateRuntime')
	return cmo.getIntendedState(appName)

def appRedeploy(appName):
	if appStatus(appName) == "STATE_ACTIVE":
		redeploy(appName)
	else:
		print "APP is not in active status."

def deployApp(appName, appFile, target):
	progress=deploy('photosearch2','E:\Oracle\user_projects\domains\JNET_Domain\\applications\photosearch2.war',targets="InquiryCluster",remote='true')
	progress.printStatus()

def appStart():
	appName = str(input("Enter app name ... "))
	startApplication(appName, timeout=30000)	# 500 seconds

def appUndeploy():
	appName = str(input("Enter app name ... "))
	appTarget = str(input("Enter target name ... "))
	undeploy(appName, targets=appTarget)

def appStop():
	appName = str(input("Enter app name ... "))
	appTarget = str(input("Enter target name ... "))
	stopApplication(appName, targets=appTarget)

def clusterState():
	clusterName = str(input("Name of Cluster ... "))
	state(clusterName,"Cluster")


if __name__ == 'main':
	print sys.argv
	parser = OptionParser()
	parser.add_option("--deploy", dest="deploy", default=False,
                  help="write report to FILE", metavar="DEPLOY")
	parser.add_option("--redeploy", dest="redeploy", default=False,
                  help="write report to FILE", metavar="REDPLOY")
	parser.add_option("--install", dest="install",
                  help="write report to FILE", metavar="INSTALL")
	parser.add_option("--status", dest="status", action="store_true",
									help="select environment", metavar="STAT")
	parser.add_option("-t", dest="test", action="store_true",
									help="select environment", metavar="ENV")
	(options, args) = parser.parse_args()
	if options.test:
		environ = "t3://10.1.26.51:7001"
	else:
		environ = "t3://10.1.226.44:7001"
	conn(environ)
	if options.install:
		print options.deploy
		appRedeploy(options.deploy)
	elif options.status:
		clusterState()
	elif options.redeploy:
		appRedeploy(options.redeploy)
	disconnect()
