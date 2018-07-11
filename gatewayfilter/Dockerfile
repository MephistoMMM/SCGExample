FROM java:8-alpine

VOLUME /tmp

ARG JAR_FILE=target/gatewayfilter-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} gatewayfilter.jar

#CMD ["java","-jar","/gatewayfilter.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gatewayfilter.jar"]

