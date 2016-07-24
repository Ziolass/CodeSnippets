#!/bin/bash
function F_TODAY {
  date --date="-0 days" +%Y%m%d
}
TODAY=$(F_TODAY)
Path="$1<name>$TODAY.csv"
echo Today_s date $TODAY
echo $Path
if [ -f $Path ];
then
	echo deleting nulls in csv
	sed -i 's/ //g' $Path
	echo import csv to postgres 
	cd /path/import_to_postgres/
	#handle inserting csv to db here
else
	echo "File $FILE does not exist."
fi
