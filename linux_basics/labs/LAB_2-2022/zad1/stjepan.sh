#!/bin/bash
sudo addgroup studenti
sudo mkdir /home/studenti -m ugo=rx
sudo chown stjepan: /home/studenti
sudo mkdir /home/studenti/studenti-shared -m 1070
sudo chown stjepan: /home/studenti/studenti-shared
sudo chgrp studenti /home/studenti/studenti-shared
echo "Generirano sve potrebno za direktorij studenti-shared."
sudo cp -r /etc/skel .
sudo mkdir skel/Documents skel/Downloads
sudo ln -s /home/studenti/studenti-shared skel/Shared
echo "Generiran skeleton."

