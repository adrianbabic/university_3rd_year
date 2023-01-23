#!/bin/bash
mkdir LAB1
cd LAB1
mkdir source
touch source/empty
cp -r /boot /etc source 2>/dev/null
du -s --si source
ln -s source target
cd target
pwd
cd ..
cd -P target
pwd
cd ..
du -sL --si target 2>/dev/null
touch --reference=source/empty source/novi
rm -r source target
cd .. && rmdir LAB1

