#!/bin/bash

REPOSITORY=/home/ec2-user/action
PROJECT_NAME=gada_api

echo "> build 파일 복사"

cp $REPOSITORY/zip/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 PID 확인"

CURRENT_PID=$(pgrep -f gada_api.jar)

echo "현재 구동중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
	echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
	echo "> kill -15 $CURRENT_PID"
	kill -15 $CURRENT_PID
	sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

chmod +x $JAR_NAME

nohup java -jar -Dspring.config.location=$REPOSITORY/application.yml $REPOSITORY/$JAR_NAME 2>&1 &