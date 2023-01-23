#!/bin/bash

mkdir zad5test
touch zad5test/PNG-07092015
touch zad5test/PNG-01011999
touch zad5test/PNG-12122012
touch zad5test/PNg-01012011
touch zad5test/PNG-0102011

find . -type f -regextype egrep -regex ".*/PNG-[0-9]{8}$" 2>/dev/null > files
file=$(cat files)
echo "Stanje direktorija prije izvodenja: "
ls ./zad5test
echo ""
for line in $file
do	
	echo $line > oneLine
	newName=$(sed -r "s:(.*/)PNG-([0-9]{2})([0-9]{2})([0-9]{4}$):\1\2_\3_\4\\.png:" oneLine)
	mv $line $newName
done
echo "Stanje direktorija nakon izvodenja: "
ls ./zad5test

rm oneLine
rm files
rm -r zad5test
