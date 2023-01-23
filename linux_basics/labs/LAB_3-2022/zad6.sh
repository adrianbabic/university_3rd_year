#!/bin/bash
cd zad6
bash setup.sh
echo ""
cd /tmp/OKOSL/downloads
find . -type f > files

if [ -d "../sorted" ] 
then
	rm -r ../sorted
	echo "Izbrisan sorted direktorij!"	
fi


mkdir ../sorted

while IFS= read -r line      #internal file separator
do
  if [[ $line =~ .*_.* ]]
  then
	
  	imePredmeta=$(echo $line | sed -r "s:^\\./([[:alnum:]]*)_(.*):\1:")
  	if [ ! -d "../sorted/$imePredmeta" ] 
  	then
  		mkdir ../sorted/$imePredmeta
  	fi
  	mv "$line" ../sorted/$imePredmeta/
  else
  	if [ ! -d "../sorted/razonoda" ] 
  	then
  		mkdir ../sorted/razonoda
	fi
   	mv "$line" ../sorted/razonoda/
  fi 
  #sleep 2
done < files

cd ../sorted/razonoda

find . -type f > files

while IFS= read -r line
do
  if [[ $line =~ .[pdf|epub]$ ]]
  then
  	if [ ! -d "../knjige" ] 
  	then
  		mkdir ../knjige
  	fi
  	mv "$line" ../knjige/
  fi
  if [[ $line =~ .[jpg|jpeg]$ ]]
  then
  	if [ ! -d "../slike" ] 
  	then
  		mkdir ../slike
  	fi
  	mv "$line" ../slike/
  fi
  if [[ $line =~ .mp3$ ]]
  then
  	if [ ! -d "../muzika" ] 
  	then
  		mkdir ../muzika
  	fi
  	mv "$line" ../muzika/
  fi
  #sleep 2
done < files

rm files



