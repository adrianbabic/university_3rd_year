#!/bin/bash
echo "DSHELL=/bin/bash" >> /tmp/addstudent.conf 
echo "DHOME=/home" >> /tmp/addstudent.conf
echo "GROUPHOMES=yes" >> /tmp/addstudent.conf    #home dir ce biti /home/groupname/user
echo "SKEL=$(pwd)/skel" >> /tmp/addstudent.conf
echo "USERGROUPS=no" >> /tmp/addstudent.conf     #nece automatski kreirati grupu s imenom usera
echo "USERS_GID=$(getent group studenti | cut -d: -f3)" >> /tmp/addstudent.conf    #getent dohvaca podatke iz NSS baza
#cut koristi delimiter ':' te uzima treci podatak iz 'splittanog polja'
echo "DIR_MODE=0750" >> /tmp/addstudent.conf #specified mode za kreiranje direktorija

file=$(cat names.txt)

for line in $file
do
    sudo adduser $line --gecos "" --disabled-password --conf /tmp/addstudent.conf
    echo "$line:student1" | sudo chpasswd
    sudo chmod 1700 /home/studenti/$line
done

echo "Dodani svi studenti"
