#!/bin/bash

mvn clean package

java -jar ./target/cloud-service-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1 2>&1 > logs &

java -jar ./target/cloud-service-0.0.1-SNAPSHOT.jar  --spring.profiles.active=peer2 2>&1 > logs &

java -jar ./target/cloud-service-0.0.1-SNAPSHOT.jar  --spring.profiles.active=peer3 2>&1 > logs &
