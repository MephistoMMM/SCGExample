FROM java:8-alpine

VOLUME /tmp
ENV EUREKA_PEERS http://localhost:8000/eureka
ENV JAR_PACKAGE test_service_caller.jar

ARG JAR_FILE=target/test_service_caller-1.0-SNAPSHOT.jar
COPY $JAR_FILE $JAR_PACKAGE

ENTRYPOINT ["java", "-Xms16m", "-Xmx48m", "-XX:MaxMetaspaceSize=64m", \
"-XX:CompressedClassSpaceSize=8m", "-Xss256k", "-Xmn8m", \
"-XX:InitialCodeCacheSize=4m", "-XX:ReservedCodeCacheSize=8m" \
"-XX:MaxDirectMemorySize=16m" \
"-Deureka.client.serviceUrl.defaultZone=$EUREKA_PEERS" \
"-jar", "test_service_caller.jar"]
