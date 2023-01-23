#!/bin/bash
file=$(cat names.txt)

for line in $file
do
    sudo deluser $line --remove-home
done

echo "Izbrisani svi studenti"
