#!/bin/bash

echo "------------서버 시작------------"
cd /home/ubuntu/toyva-server
sudo fuser -k -n tcp 8080 || true
nohub java -jar project.jar > ./output.log 2>&1 &
echo "----------서버 배포 완료----------"