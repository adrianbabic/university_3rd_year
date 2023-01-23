#!/bin/bash
echo "Broj redova koji su uvuceni: "
egrep -c "^[[:blank:]].*" /usr/include/stdio.h
echo "Broj funkcija s argumentom __args: "
egrep -c ".*__arg[,)].*" /usr/include/stdio.h

