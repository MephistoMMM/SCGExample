if [ ! -f ./addr/target/addr-0.0.1-SNAPSHOT.jar ]; then
    cd addr
    mvn clean package
    cd ..
fi
if [ ! -f ./image/target/client-0.0.1-SNAPSHOT.jar ]; then
    cd image
    mvn clean package
    cd ..
fi
if [ ! -f ./SpringBootThymeleaf/target/SpringBootThymeleaf-1.0-SNAPSHOT.jar ]; then
    cd SpringBootThymeleaf
    mvn clean package
    cd ..
fi
if [ ! -f ./time/target/time-0.0.1-SNAPSHOT.jar ]; then
    cd time
    mvn clean package
    cd ..
fi
if [ ! -f ./weather/target/weather-0.0.1-SNAPSHOT.jar ]; then
    cd weather
    mvn clean package
    cd ..
fi
if [ ! -f ./test_server_caller/target/test_service_caller-1.0-SNAPSHOT.jar ]; then
    cd test_server_caller
    mvn clean package
    cd ..
fi
