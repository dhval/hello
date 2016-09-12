#### Select a random file from directory. - [ SO ] (http://stackoverflow.com/questions/701505/best-way-to-choose-a-random-file-from-a-directory-in-a-shell-script)

```
files=(/my/dir/*)
printf "%s\n" "${files[RANDOM % ${#files[@]}]}"
```
===

#### Rename Files, clear dirty stuff 

```
ls | while read -r FILE
do
   mv "$FILE" `echo $FILE | tr ' ' '_' | tr -d '[{}(),\!]' | tr -d "\'" | tr '[A-Z]' '[a-z]' | sed 's/_-_/_/g'`
done
```

#### OSX Hosts File

```
sudo nano /private/etc/hosts
dscacheutil -flushcache
```