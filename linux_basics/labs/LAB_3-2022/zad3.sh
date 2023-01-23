#!/bin/bash
cat > top10.txt << EOF
Linux Mint 19.1
Ubuntu 20.4
Debian GNU/Linux 10.7
Mageia 7.1
Fedora 33
openSUSE Leap 15.2
Arch Linux
CentOS 8
PCLinuxOS
Slackware Linux 14.2
FreeBSD
EOF
echo "Ispis sadrzaja datoteke prije obrade:"
echo ""
cat top10.txt 
echo ""
sed -ri "/^[^0-9]*$/d" top10.txt
sed -ri "s/(^[^0-9]*)([0-9\\.]*)/\2 \1/" top10.txt
sed -ri "s/(.*)/\L\1/" top10.txt
sed -ri "y/aeiou/AEIOU/" top10.txt
echo "Ispis sadrzaja datoteke poslije obrade:"
echo ""
cat top10.txt
rm top10.txt
