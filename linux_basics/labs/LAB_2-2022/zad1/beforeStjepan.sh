#!/bin/bash
echo "$(cat /etc/sudoers.d/stjepan), /usr/bin/bash, /usr/bin/mkdir, /usr/bin/chgrp, /usr/bin/cp, /usr/bin/ln, /usr/sbin/chpasswd" > /etc/sudoers.d/stjepan
echo "Dodane potrebne naredbe u /etc/sudoers.d/stjepan !"

