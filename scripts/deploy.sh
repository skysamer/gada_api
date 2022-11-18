#!/bin/bash

REPOSITORY=/home/ec2-user/action
PROJECT_NAME=gada_api

echo "> build 파일 복사"

cp $REPOSITORY/zip/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 PID 확인"

CURRENT_PID=$(pgrep -f gada_api.jar)

echo "현재 구동중인 애플리케이션 pid: $CURRENT_PID"

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

chmod 777 /home/ec2-user/action/$JAR_NAME

nohup java -jar \
-Dspring.config.location=/home/ec2-user/action/application-real8400.yml \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

sleep 25

echo "set \$service_url http://127.0.0.1:8400;" | sudo tee /etc/nginx/conf.d/service-url.inc

result_value=$(netstat -nap 2>/dev/null | grep 8600 | awk '{print $7}')
number_value=${#result_value}

pid_val=${result_value%%'/'*}

kill -9 $pid_val

#if [ -z "$CURRENT_PID" ]; then
#	echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
#else
#	echo "> kill -15 $CURRENT_PID"
#	kill -15 $CURRENT_PID
#	sleep 5
#fi