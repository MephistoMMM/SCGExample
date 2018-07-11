FROM java:8-alpine

VOLUME /tmp

ARG JAR_FILE=target/time-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} time.jar

ENTRYPOINT java -Xms16m -Xmx48m -XX:MaxMetaspaceSize=64m \
-XX:CompressedClassSpaceSize=8m -Xss256k -Xmn8m \
-XX:InitialCodeCacheSize=4m -XX:ReservedCodeCacheSize=8m \
-XX:MaxDirectMemorySize=16m
