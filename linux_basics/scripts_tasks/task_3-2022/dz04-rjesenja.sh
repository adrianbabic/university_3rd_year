#!/usr/bin/bash

# Obavezno prije rjesavanja detaljno procitajte pdf datoteku zadace kao i upute
# za rjesavanje domacih zadaca u repozitoriju kolegija!

# Rjesenja podzadataka 2-6 potrebno je upisati u datoteku stvorenu u podzadatku
# 1, svako rjesenje u novi red.  Svi podzadaci odnose se na trenutnog
# korisnika. Podzadatke treba rijesiti dinamicki - nije dozvoljeno
# ”hardkodirati” ime korisnika. Nije dozvoljeno kreirati vlastite varijable vec
# je potrebno iskoristiti varijable okruzenja i/ili naredbe koje su vam na
# raspolaganju.

#!#!#!#!#!#!#
# 1. Kreirajte datoteku imenovanu kao korisnik koji trenutacno koristi
# terminal. (0.5)
touch "$USER".txt


#!#!#!#!#!#!#
# 2. Upisite korisnikovo ime u datoteku (ne pravo ime nego korisnicko ime).
# (0.5)
echo "USER: $USERNAME" >> "$USERNAME".txt


#!#!#!#!#!#!#
# 3. Upisite putanju korisnikovog maticnog direktorija. (0.5)
echo "HOME DIR: $HOME" >> "$USERNAME".txt


#!#!#!#!#!#!#
# 4. Upisite korisnikovu ljusku. (0.5)
echo "SHELL: $SHELL" >> "$USERNAME".txt


#!#!#!#!#!#!#
# 5. Upisite imena grupa kojima korisnik pripada. (0.5)
echo "GROUPS: $(id -nG)" >> "$USERNAME".txt


#!#!#!#!#!#!#
# 6. Upisite ime samo korisnikove primarne grupe. (0.5)
echo "PRIMARY GROUP: $(id -ng)" >> "$USERNAME".txt


#!#!#!#!#!#!#
# 7. Izbrisite datoteku iz prethodnih podzadataka. (0.5)
rm "$USERNAME".txt


#!#!#!#!#!#!#
# 8. Kreirajte novog korisnika weakling. (0.5)
sudo adduser weakling


#!#!#!#!#!#!#
# 9. Omogucite korisniku weakling privremeno dobivanje administrativnih
# ovlasti. (0.75)
sudo usermod -aG root weakling


#!#!#!#!#!#!#
# 10. "Prebacite se" u korisnika weakling - prijavite se u ljusku kao taj
# korisnik, i to u njegov maticni direktorij i s njegovim varijablama okruzenja
# (0.5)
su - weakling


#!#!#!#!#!#!#
# 11. Bez promjene direktorija, stvorite novu praznu datoteku u /tmp. Datoteku
# nazovite samo-ja. (0.5)
touch /tmp/samo-ja


#!#!#!#!#!#!#
# 12. Pobrinite se da s datotekom samo-ja nitko (ni grupa ni ostali) ne smije
# nista ciniti, osim vlasnika koji ju smije samo citati. (0.5)
chmod u=r,go=-r-w-x /tmp/samo-ja


#!#!#!#!#!#!#
# 13. Bez promjene ostalih dozvola, omogucite ostalim korisnicima da u datoteku
# samo-ja mogu pisati (0.5)
chmod o=w /tmp/samo-ja


#!#!#!#!#!#!#
# 14. Ispisite vlasnika-grupu datoteke samo-ja. (0.5)
stat -c "%G" /tmp/samo-ja


#!#!#!#!#!#!#
# 15. Odjavite se iz korisnika weakling. (0.25)
exit


#!#!#!#!#!#!#
# 16. Izbrisite korisnika weakling, i pobrinite se da i njegov maticni
# direktorij bude takoder obrisan (0.5)
sudo deluser --remove-home weakling


#!#!#!#!#!#!#
# 17. Za one koji zele znati vise (neobavezan zadatak): Smjestite korisnika weakling u /root direktorij
# (postignite da trenutni radni direktorij bude /root).
sudo usermod -d /root weakling
#probao sam ovo ali nije uspjelo :( zanima me rjesenje :)


#!#!#!#!#!#!#
