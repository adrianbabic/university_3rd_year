#!/bin/bash
echo "Second script started"
kapsule=0
komprimirane=0
sumece=0
while true
do
	trap '((kapsule++)); echo "Kapsule: $kapsule";' SIGUSR1
	trap '((komprimirane++)); echo "Komprimirane tablete: $komprimirane";' SIGBUS
	trap '((sumece++)); echo "Sumece tablete: $sumece";' SIGALRM
	trap 'echo ""; echo "End of task"; echo "Kapsule: $kapsule";echo "Komprimirane tablete: $komprimirane";echo "Sumece tablete: $sumece"; exit 0;' SIGPROF
	sleep 1
done
