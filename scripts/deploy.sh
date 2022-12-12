#!/bin/bash

REPOSITORY=/home/ec2-user/action
PROJECT_NAME=gada_api

echo "> build 파일 복사"

cp $REPOSITORY/zip/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 PID 확인" >> debug.log

CURRENT_PID=$(pgrep -f gada_api.jar)

echo "현재 구동중인 애플리케이션 pid: $CURRENT_PID" >> debug.log

echo "> 새 애플리케이션 배포" >> debug.log

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

chmod 777 /home/ec2-user/action/$JAR_NAME

nohup java -jar \
-Dspring.config.location=/home/ec2-user/action/application-real8400.yml \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

sleep 40

echo "> 리버스 프록시 작업" >> debug.log

echo "set \$service_url http://127.0.0.1:8400;" | sudo tee /etc/nginx/conf.d/service-url.inc

sudo systemctl restart nginx

echo "> 기존에 구동중인 애플리케이션 프로세스 제거 작업" >> debug.log

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi