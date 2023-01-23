#!/bin/bash
echo "First script has started!"
bash second.sh &
# Citanje PID -a druge skripte
SECOND_PID=$!
prekid=0
echo "Second pid: $SECOND_PID"
while true
do
	sleep 1
	sigval=$((1 + RANDOM %3))
	
	case "$sigval" in
	   	"1") 
	   		kill -SIGUSR1 $SECOND_PID 
   		;;
		"2") 
			kill -SIGBUS $SECOND_PID 
   		;;
   		"3") 
   			kill -SIGALRM $SECOND_PID 
		;;
	esac
	trap 'prekid=1' SIGINT
	if [ $prekid = 1 ]
	then
		kill -SIGPROF $SECOND_PID
		sleep 1
		exit 0
	fi
done
