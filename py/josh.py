import ldap
ldap.set_option(ldap.OPT_REFERRALS, 0)
l = ldap.initialize("ldap://10.1.26.4")
l.protocol_version = ldap.VERSION3
username = "cn=certexpiration,ou=ServiceAccounts,o=jnet"
password  = "cert2010"
l.simple_bind_s(username, password)

## The next lines will also need to be changed to support your search requirements and directory
baseDN = "ou=Active,ou=People,o=jnet"
searchScope = ldap.SCOPE_SUBTREE
## retrieve all attributes - again adjust to your needs - see documentation for more options
retrieveAttributes = ["cn", "jnetpin", "sn"]
searchFilter = "(cn=josh*)"

try:
	ldap_result = l.search_s("ou=Active,ou=People,o=jnet", ldap.SCOPE_SUBTREE, searchFilter, retrieveAttributes)
	for cn, length in ldap_result:
		print length
except ldap.LDAPError, e:
	print e


