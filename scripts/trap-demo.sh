#!/bin/bash

###  http://stackoverflow.com/questions/11203201/how-to-stop-infinite-loop-in-bash-script-gracefully ###

echo  "To stop me :"
echo  "kill -stop $0"

PID_FILE=$(basename $0)".pid"
ABORT=0

echo  "$PID_FILE :  $$"
echo  "$$" > $PID_FILE

trap  stop stop

stop() {
  echo "int stop";
  $ABORT=1
  exit 0
}

#  Use (( since its numerical comparison

while (( $ABORT != 1 ));  do
  echo  "d"
  sleep 2
done



