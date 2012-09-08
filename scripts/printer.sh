#!/bin/bash
echo $@ ' -> echo $@'

if [ $# -eq 0 ] | [ ! -f $1 ]; then
	echo "Input: PRINTER_CAPABILITIES_REPORT_20120719134531_232.csv *MS81* "
	exit 1
fi
rm printer.log
grep -E "$2" $1 | sed -e 's|["\""]|'\''|g' | sed -e 's|'\ '|'_'|g' > printer.log
wc -l printer.log
TIMES=1;
for line in `cat printer.log`; do
	myHost=`echo $line | cut -d, -f 1 | tr -d "\'"`
	#echo "$myHost $line"
	count=$(ping -4 -n $TIMES $myHost | grep -i "received" | awk -F',' '{ print $2 }' | awk -F'= ' '{ print $2}')
	#echo "$count" 
	if [ ! $count -eq 0 ]; then
		# 100% failed 
		echo "$line, '$(date +"%m-%d-%Y")' online"
	fi

done
