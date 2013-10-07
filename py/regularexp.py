
import re

def Find ( pattern, txt):
     match = re.search( pattern, txt)
     if match: print match.group()
     else: print "pattern not found"

Find(r'\d', 'im a 420')


