FROM java:8-alpine

# if you don't want use cache layers, change this data
ENV REBUILD_DATA 2018.7.11

EXPOSE 8000
VOLUME /tmp

ARG JAR_FILE=target/gatewayfilter-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

CMD ["java", "-jar", "app.jar"]
