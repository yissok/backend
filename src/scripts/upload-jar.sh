#!/bin/bash

#export AWS_ACCESS_KEY_ID=<your_access_key>
#export AWS_SECRET_ACCESS_KEY=<your_secret_key>
#export AWS_DEFAULT_REGION=us-east-1

#install java and gradle

S3_BUCKET=andreaciao
S3_OBJECT_KEY=backend/jars
SPRING_BOOT_APP_NAME=myapp

#./gradlew clean build
#executable_jar=$(ls build/libs/*-SNAPSHOT.jar)
#aws s3 cp "$executable_jar" "s3://$S3_BUCKET/$S3_OBJECT_KEY/$SPRING_BOOT_APP_NAME-executable.jar"

executable_jar=$(ls src/scripts/upload-jar.sh)
aws s3 cp "$executable_jar" "s3://$S3_BUCKET/$S3_OBJECT_KEY/$SPRING_BOOT_APP_NAME-executable.sh"
