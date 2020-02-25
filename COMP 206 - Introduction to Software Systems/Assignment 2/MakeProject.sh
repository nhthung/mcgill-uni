#!/bin/bash

if [ $# = 0 ]
	then
		echo "Pick a name for your project pls"
		exit 1	
fi


cd /home/2017/hle30
if [ ! -d project ]; then
	mkdir project
fi
cd project


if [ ! -d cs206 ]; then
	mkdir cs206
fi
cd cs206


if [ -d $1 ]
	then
		echo "This project name is already in use"
		exit 1
else
	mkdir -p $1/{archive,backup,docs,assets,database,source}
	cd $1/source
	echo -e "#!/bin/bash\ncp *.c *.h ../backup" > backup.sh
	chmod +x backup.sh
	echo "Your project directories have been created."
	exit 0
fi