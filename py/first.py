### specify encoding ###

# -*- coding: utf-8 -*-

print "#" * 10

# use floating numbers
print "hello", 7.0/4.2

# printing & formatting
print "my name is %s and I am %d old" % ('dhval', 30)

# input
print "who are you?",
dhval = raw_input()
print dhval

# lets try some loops

i=0
number = []

while i<= 5:
    number.append(i)
    i += 1
    print "number", number

for n in number:
    print "number %d " % n


