#!/bin/bash
echo $@ ' -> echo $@' $#

if [ $# -ne 1 ] || [ ! -d $1 ]; then
	echo "Input: Not a directory "$1
	exit 1
fi

baseDir=$1;

for line in `cd $baseDir && ls -1d */`; do
	fullPath=$baseDir"/"$line
	echo "----- $fullPath -----";
	echo `cd $fullPath && git stash && git pull --rebase && git stash pop`;
done