#!/usr/bin/bash

#!#!#!#!#!#!#
# 1. Koristeci naredbu echo i preusmjeravanje stvorite datoteku naziva dz02.dat
# koja sadrzi danasnji datum u sebi. (Datum ne smije biti hard-kodiran) (1.0)
echo $(date "+%d-%m-%Y") > dz02.dat


#!#!#!#!#!#!#
# 2. Koristeci naredbu cat i here dokument dodajte string "OKOSL" u redak ispod
# datuma u datoteci iz proslog zadatka. U ovom zadatku je dozvoljeno rjesenje s
# vise linija. (0.5)
cat >> dz02.dat << EOF
"OKOSL"
EOF

#!#!#!#!#!#!#
# 3. Pomocu naredbe tee dodajte redak "linux <3" na kraj datoteke iz prosla dva
# zadatka. (0.5)
echo "linux <3" | tee --append dz02.dat



#!#!#!#!#!#!#
# 4. Sortirajte datoteku korisnici.dat uzlazno po broju ispred imena korisnika
# te rezultat zapisite u datoteku korisnici.sortirano.dat. (0.75)
cat korisnici.dat | sort -n > korisnici.sortirano.dat



#!#!#!#!#!#!#
# 5. Ispisite sva jedinstvena korisnicka imena iz datoteke korisnici.dat u
# datoteku jedinstveni_korisnici.dat. Racunajte da su imena u datoteci case
# insensitive. (1.0)
grep -io "[A-Z]*" korisnici.dat | tr '[:upper:]' '[:lower:]' | sort | uniq -iu > jedinstveni_korisnici.dat



#!#!#!#!#!#!#
# 6. Ispisite sva nejedinstvena korisnicka imena iz datoteke korisnici.dat u
# datoteku nejedinstveni_korisnici.dat. Racunajte da su imena u datoteci case
# insensitive. (1.0)
grep -io "[A-Z]*" korisnici.dat | tr '[:upper:]' '[:lower:]' | sort | uniq -id > nejedinstveni_korisnici.dat



#!#!#!#!#!#!#
# 7. Prebrojite koliko rijeci iz datoteke /usr/share/dict/words sadrzi tekst
# ping. (0.5)
grep "ping" /usr/share/dict/words | wc -l


#!#!#!#!#!#!#
# 8. U datoteku yous.dat izdvojite sve rijeci iz datoteke /usr/share/dict/words
# koje sadrze tekst you. (0.5)
grep "you" /usr/share/dict/words > yous.dat


#!#!#!#!#!#!#
# 9. Prebrojite broj rijeci iz proslog zadatka. (0.50)
grep "you" /usr/share/dict/words | wc -l


#!#!#!#!#!#!#
# 10. Locirajte datoteku jedinstveni_korisnici.dat koristeci naredbu find.
# (0.5)
find / -name jedinstveni_korisnici.dat 2>/dev/null



#!#!#!#!#!#!#
# 11. Locirajte datoteku jedinstveni_korisnici.dat koristeci naredbu locate.  U
# ovom zadatku je dozvoljeno rjesenje s vise linija. (0.5)
locate jedinstveni_korisnici.dat



#!#!#!#!#!#!#
# 12. Koristeci operator | odnosno pipe ispisite sve datoteke iz home
# direktorija trenutnog korisnika kojima je zadnje vrijeme promjene (eng.
# modification time) bilo u 10. mjesecu. (HINT: dovoljno je koristiti naredbu
# ls te grep na "Oct", odnosno "Lis") (0.75)
ls -lp ~ | grep -v / | grep "lis"


#!#!#!#!#!#!#
