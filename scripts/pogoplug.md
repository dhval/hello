```
cat warrant.xml | grep -o "<[[:alpha:]]*>" \
 | sed "s/<//" | sed "s/>//" \
 | awk '{print "String "$1" = \""$1"\";"}'
```

#### To get a list of packages installed locally do this in your terminal:

```dpkg --get-selections | grep -v deinstall```

#### To allow incoming tcp packets on port 53

```sudo ufw allow 53/tcp```

#### Use apt-get command to forcefully reinstall package.

```apt-get --reinstall install PackageNameHere```

#### Plex stuff

```
sudo service plexmediaserver start
http:// localhost:32400/web/index.html#!/dashboard
sudo nano /etc/default/plexmediaserver
```

#### Transmission

```
sudo usermod -a -G debian-transmission user
sudo chgrp -R debian-transmission /home/user/Downloads/transmission
sudo chmod -R 775 /home/user/Downloads/transmission
/etc/transmission-daemon/settings.json
sudo service transmission-daemon reload|start|stop
```

#### Mac changing SSH port

```
/etc/services
/System/Library/LaunchDaemons/ssh.plist
launchctl load|unload /System/Library/LaunchDaemons/ssh.plist
```
