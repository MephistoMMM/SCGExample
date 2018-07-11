#!/bin/bash

mvn clean package

java -jar ./target/cloud-discovery-0.0.1-SNAPSHOT.jar
