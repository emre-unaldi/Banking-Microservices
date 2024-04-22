FROM openjdk:21
LABEL author="emreunaldi <emree.unaldi@gmail.com>"
WORKDIR /account-service
ADD target/account-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "account-service-0.0.1-SNAPSHOT.jar"]