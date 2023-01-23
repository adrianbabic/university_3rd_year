#!/bin/bash
adduser stjepan --no-create-home --shell /bin/bash --gecos ""
echo "stjepan ALL=(root) NOPASSWD: STJEPANS_CMND" >> /etc/sudoers.d/stjepan
echo "Cmnd_Alias STJEPANS_CMND = /usr/sbin/adduser, /usr/sbin/deluser, /usr/sbin/addgroup, /usr/sbin/delgroup, /usr/bin/chown, /usr/bin/chmod" >> /etc/sudoers.d/stjepan

