#!/bin/bash

PROJECT_ROOT=`pwd`

echo "AUTO BUILD MAVEN..."
cd micro-services
./auto-build-mvn.sh
cd $PROJECT_ROOT
echo "FINISH BUILDING MAVEN"

echo "AUTO BUILD EUREKA SERVICE..."
docker images | grep eureka > /dev/null
if [ $? -eq 1 ]; then
    cd eureka-service
    docker build -t eureka:1.0 .
    cd $PROJECT_ROOT
else
    echo "EUREKA SERVICE HAS EXISTED"
fi
echo "FINISH BUILDING EUREKA SERVICE"

echo "START EUREKA SERVICE..."
echo "RUN: docker-compose -f ./eureka-service/docker-compose.yml up -d"
docker-compose -f ./eureka-service/docker-compose.yml up -d

echo "START MICRO SERVICES..."
echo "RUN: docker-compose -f ./micro-services/docker-compose.yml up -d $*
"
docker-compose -f ./micro-services/docker-compose.yml up -d $*

echo "BOOT UP!"
