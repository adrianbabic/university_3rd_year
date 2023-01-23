#!/bin/bash
deluser stjepan --remove-home
rm -r /etc/sudoers.d/stjepan
rm -r /home/studenti
rm -r skel
rm addstudent.conf
#bash deleteStudents.sh
delgroup studenti
echo "Obrisan stjepan, studenti, /home/studenti, skeleton i addstudent.conf."


#personal use only, not part of the task
