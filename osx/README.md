Use [Route](https://developer.apple.com/library/mac/documentation/Darwin/Reference/Manpages/man8/route.8.html) for different sub nets.

on Linux

```
sudo route add -net 10.1.0.0/16 gw 172.19.10.1
```

On Mac

```
sudo route -n add -net 10.1.0.0/16 172.19.10.1
```

JAVA_HOME

```
export JAVA_HOME=$(/usr/libexec/java_home)
```

Mount windows share [Mount the share: ~/Library/Preferences/nsmb.conf](https://developer.apple.com/library/mac/documentation/Darwin/Reference/ManPages/man8/mount_smbfs.8.html)

```
mount_smbfs //username:password@server.name/share_name share_name/
mount_smbfs -d 755 -f 755 //weblogic/e$/Oracle/TEMP Temp/
umount shr_name
```

The hosts file

```
sudo nano /private/etc/hosts
dscacheutil -flushcache
```

[Resize disk image](http://apple.stackexchange.com/questions/60613/change-dmg-capacity)

```
hdiutil resize -size 25G /PATH/TO/DISK/IMAGE.dmg
```

Update default SSH port

```
/etc/services
/System/Library/LaunchDaemons/ssh.plist
launchctl load|unload /System/Library/LaunchDaemons/ssh.plist
```

Disable google updater

```
defaults write com.google.Keystone.Agent checkInterval 0
```


