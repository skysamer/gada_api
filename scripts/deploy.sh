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
-Dspring.config.location=/home/ec2-user/action/application-real8600.yml \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

sleep 60

echo "set \$service_url http://127.0.0.1:8600;" | sudo tee /etc/nginx/conf.d/service-url.inc

sudo systemctl restart nginx

# 여기는 기존에 올라간 포트

result_value=$(netstat -nap 2>/dev/null | grep 8400 | awk '{print $7}')
number_value=${#result_value}

if [ $number_value == 0 ]; then
     echo '해당포트가 미사용중으로 이미 종료되었습니다..'
else
     echo '종료를 시작합니다.'
     pid_val = ${result_value%%'/'*}
     kill -9 $pid_val
     if [ $? == 0 ]; then
    echo '정상종료'
     else
        echo '종료 실패'
    fi
fi