files=(/dir/*)
echo "${files[RANDOM % ${#files[@]}]}"

