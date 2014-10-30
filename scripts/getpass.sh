#!/bin/bash

# https://github.com/tjpatter/getpass/tree/master/include
# SLE-11-SP3-SDK-DVD-x86_64-GM-DVD1.iso
# https://www.novell.com/communities/coolsolutions/cool_tools/getpass-21-universal-password-retrieval-utility-updated/

LDAP_SERVER="10.1.26.4"
DN_ADMIN="cn=admin,o=jnet"
ADMIN_PWD="Novell"
DN_USER=",ou=active,ou=people,o=jnet"

if [ $# != 1 ] || [ -z "$1" ]; then
	echo "Please provide username !"
	exit 1
fi

echo "Looking for username:" $1

/usr/local/sbin/getpass/getpass $LDAP_SERVER $DN_ADMIN $ADMIN_PWD "cn="$1$DN_USER
