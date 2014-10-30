import re

with open("C:\Users\c-dmudawal\Desktop\users.csv", "r") as r, \
open("C:\Users\c-dmudawal\Desktop\data.csv", "w+") as w:
	for line in r:
		line = line.strip().replace('"','')
		# extract lines containing string e.g."firstname.lastname"
		match =  re.match("^[\w]*\.[\w]*$",line)
		# extract a user containing string "vani"
		if match is not None and re.match(".*vani.*", line, re.I):
			w.write(line + "\n")