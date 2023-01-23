#!/bin/bash

#pretrazivani su speficificni direktoriji za krace trajanje pretrazivanja

find /usr/share -type f -regextype egrep -regex ".*/.*\\.py$" 2>/dev/null > files
file=$(cat files)
for line in $file
do	
	echo $line
	sed -rn "s/.*def ([[:print:]^[:blank:]]*)\\(.*/\1/p" $line
	echo ""
done
echo "Zavrsen ispis svih imena funkcija unutar .py datoteka"
sleep 3

find /usr/include -type f -regextype egrep -regex ".*/.*\\.[c|h]$" 2>/dev/null > files
file=$(cat files)
for line in $file
do	
	echo $line 
	sed -rn "s/(^#.*)/\1/p" $line
	echo ""
done
echo "Zavrsen ispis svih pretprocesorskih naredbi unutar c datoteka"
sleep 3

find /usr/include -type f -regextype egrep -regex ".*" 2>/dev/null > files
file=$(cat files)
for line in $file
do	
	output=$(egrep -n ".*include.*" $line | head -n1 | cut -d ":" -f1)
	if [ "$output" != "" ];
	then
    		echo $line
	    	echo $output
	    	echo ""
	fi
done
echo "Zavrsen ispis svih datoteka koje sadrze niz 'include'."


rm files

