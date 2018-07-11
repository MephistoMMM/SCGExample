#!/bin/bash

mvn clean package

java -jar ./target/cloud-register-0.0.1-SNAPSHOT.jar
