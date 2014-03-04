
### Use tor and polipo proxy ###

# export http_proxy=http://localhost:8118/ https_proxy=http://localhost:8118/
# youtube-dl --verbose  -o

OUTPUT_DIR=$1
PATH_TO_FILE=$2
FILE_FORMAT="%(title)s-%(id)s.%(ext)s"

if [ ! -d $OUTPUT_DIR ] || [ ! -f $PATH_TO_FILE]; then
	echo "Missing file or directory";
	echo "Usage: /OUTPUT_DIR /PATH_TO_FILE"
	exit 0
fi	

while true
do
	for i in `cat "$PATH_TO_FILE"`;
		echo $i
		do youtube-dl --verbose  -o $OUTPUT_DIR$FILE_FORMAT $i;
		sleep 5;
	done
done

