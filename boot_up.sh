#!/bin/bash

PROJECT_ROOT=`pwd`

type -a docker
if [ $? -eq 1 ]; then
    echo "PLEASE INSTALL docker ."
    exit 1
fi

type -a docker-compose
if [ $? -eq 1 ]; then
    echo "PLEASE INSTALL docker-compose ."
    exit 1
fi

echo "AUTO BUILD MAVEN..."
cd micro-services
./auto-build-mvn.sh
cd $PROJECT_ROOT
echo "FINISH BUILDING MAVEN"

echo "AUTO START GATEWAY SERVICE..."
docker images | grep gateway > /dev/null
if [ $? -eq 1 ]; then
    cd gatewayfilter
    docker-compose up -d
    cd $PROJECT_ROOT
else
    echo "GATEWAY SERVICE HAS EXISTED"
fi
echo "FINISH STARTING GATEWAY SERVICE"

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

echo "CREATE COMMON NETWORK..."
docker network create spring_cloud
echo "FINISH CREATING COMMON NETWORK..."

echo "START EUREKA SERVICE..."
echo "RUN: docker-compose -f ./eureka-service/docker-compose.yml up -d"
docker-compose -f ./eureka-service/docker-compose.yml up -d

echo "START MICRO SERVICES..."
echo "RUN: docker-compose -f ./micro-services/docker-compose.yml up -d $*
"
docker-compose -f ./micro-services/docker-compose.yml up -d $*

echo "BOOT UP!"
