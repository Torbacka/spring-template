#!/usr/bin/env bash

if [ "$1" == "--docker" ]; then
	EXTRACONF="../$2/.circleci/docker-build.yml"
	shift
elif [ "$1" == "--gcloud" ]; then
	EXTRACONF="../$2/.circleci/gcloud-deploy.yml"
	shift
elif [ "${1:0:1}" == "-" ]; then
	echo "Error \"$1\" is an invalid option"
	exit 1
else
	echo
	echo "WARNING: No deplyment target specified, considder using $0 [--docker|--gcloud] $2"
	echo
fi

REMOTE=$(grep "url = " .git/config|cut -d "=" -f 2|sed s/spring-template/$1/g|tr -d ' ')

if [ -z $1 ]; then
	echo "No argument given. Expecting name of project to create."
	echo "This must be exactly as it is named on GitHub."
	exit
fi

if [ -a "../$1" ]; then
	echo "ERROR: The directory $(realpath ../$1) already exists."
	exit
fi

cp -r . "../$1" &&
sed s,ProjectTemplate,$1,g .circleci/config.yml > ../$1/.circleci/config.yml
mv "../$1/deploy/ProjectTemplate" "../$1/deploy/$1"
sed -i s,ProjectTemplate,$1,g ../$1/deploy/$1/*.yaml

if [ -n "$EXTRACONF" ]; then
	cat $EXTRACONF >> ../$1/.circleci/config.yml
	if [ -n "$GITHUB_TOKEN"  ]; then
		echo "Attempting to create repo on GitHub"
		curl https://$GITHUB_TOKEN@api.github.com/orgs/zensum/repos -X POST -d "{\"name\": \"$1\",  \"private\": true}"|grep full_name || echo "There was an error creating the repo :("
	else
		echo "No GitHub token found in \$GITHUB_TOKEN get yours from https://developer.github.com/v3/guides/getting-started/#authentication I need a username:apitoken pair"
	fi
fi

cd "../$1" &&

echo "# $1" > README.md &&
if [ -n "$2" ]; then
	echo "$2" >> README.md
	sed s,"description of project","$2",g build.gradle > build.gradle_
	mv -f build.gradle_ build.gradle
fi
echo "rootProject.name = '$1'" > settings.gradle &&

rm -fr src/main/java src/main/kotlin build/ &&
rm -fr .git &&
rm -fr .idea
rm -f *.iml *.ipr *.iws
rm -f .circleci/docker-build.yml .circleci/gcloud-deploy.yml
gradle wrapper --gradle-version 4.7
git init &&
git config pull.rebase true
git config core.fileMode false
git remote add origin $REMOTE &&
git add -A &&
git reset -- $0 &&
rm -f $0
git commit -m 'First commit.'
mkdir -pv src/main/java/io/klira
