#!/usr/bin/python

### Utility to keep track of duplicate files.

import argparse, os, sys, sqlite3, hashlib, magic

# Returns file size in kb (divide by float 1024.00)
def find_file_size(file):
	if not os.path.exists(file):
		return 0
	else:
		return os.path.getsize(file)/1024.00

# List files, os.walk returns 3-tuple
# http://stackoverflow.com/questions/5141437/filtering-os-walk-dirs-and-files
def list_files(path, type=None):
	for(dirpath, dirnames, filenames) in os.walk(path):
		file = [ f for f in filenames if len(filenames)>0 and (type is None or f.endswith(type)) ]
		print dirpath, "---", dirnames, "---", file
		print "\n"

# SHA1 hash
# http://stackoverflow.com/questions/3431825/generating-a-md5-checksum-of-a-file
# http://blog.liw.fi/posts/rsync-in-python/
def file_sha1(file):
	BLOCKSIZE = 65536
	hasher = hashlib.sha1()
	with open(file, 'rb') as afile:
		buf = afile.read(BLOCKSIZE)
		while len(buf) > 0:
			hasher.update(buf)
			buf = afile.read(BLOCKSIZE)
	print(hasher.hexdigest())

def file_type(file):
	'''
	pip install python-magic
	brew install libmagic
	'''
	mime = magic.Magic(mime=True)
	return mime.from_file(file)

if __name__ == "__main__":

	print "%f.1", list_files(sys.argv[1])