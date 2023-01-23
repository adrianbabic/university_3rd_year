#!/ bin/ bash ...
echo " U godini $godina , petak 13. se dogodio $petkovi puta . "
echo -n " Upisi godinu za do koje te zanima koliko puta ce se dogoditi petak 13.: "
read opseg
count=0
for godina in $( seq 2022 $opseg );
do
	petkovi=$( ncal $godina | grep ^pe | grep -o 13 | wc -l )
	echo " U godini $godina , petak 13. se dogodio $petkovi puta . "
	count=$(expr $count + $petkovi)
done
echo "Od 2022 do $opseg sveukupno se petak 13. dogada $count puta ."

