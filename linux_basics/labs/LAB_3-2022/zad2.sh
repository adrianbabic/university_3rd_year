#!/bin/bash

mkdir prviDir
mkdir drugiDir
touch prviDir/cabcd11.dd 	#should print (1)
touch prviDir/cabcd11.34
touch drugiDir/celje.d
touch prviDir/celjed11d
touch prviDir/lav32.d
touch drugiDir/zzzz1.z  #should print (2)
touch drugiDir/xxxx.z
touch prviDir/yyy1k 
touch prviDir/yyy1l	#should print (2)

echo "Datoteke koje pocinju sa slovom c, iza toga imaju niz slova, dva broja, tocku i barem jedno slovo iza tocke:"
find . -type f -regextype egrep -regex ".*/c[[:alpha:]]*[[:digit:]]{2}\\.[[:alpha:]]+[^/]*$" 2>/dev/null
echo "Datoteke koje ne sadrze mala slova a-k te imaju barem jednu znamenku: "
find . -type f -regextype egrep -regex ".*/[^a-k^/]*[0-9][^a-k^/]*$" 2>/dev/null

rm -r prviDir
rm -r drugiDir
