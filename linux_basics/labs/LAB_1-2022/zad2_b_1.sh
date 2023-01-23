#!/bin/bash
touch razgovor.txt
tail -f razgovor.txt & cat >> razgovor.txt
#konsole --noclose -e cat >> razgovor.txt
#konsole --noclose -e tail -f razgovor.txt
rm razgovor.txt

#cat naredba proslijeduje input jednog shella u datoteku razgovor.txt

#pokusao sam sa otvaranjem druge konzole no nije mi islo
#ideja mi je bila da paraleleno obje ljuske izvode istovremeno i cat naredbu i tail naredbu,
#ali ocito nije ispravna ideja ili je nisam mogao tocno izvesti


