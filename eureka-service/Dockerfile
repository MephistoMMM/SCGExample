FROM java:8-alpine

MAINTAINER Mephis Pheies <mephistommm@gmail.com>

# if you don't want use cache layers, change this data
ENV REBUILD_DATA 2018.7.11

EXPOSE 8000
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp

CMD ["java", "-Deureka.client.serviceUrl.defaultZone=$EUREKA_PEERS", "-jar", "./target/cloud-service-0.0.1-SNAPSHOT.jar"]
