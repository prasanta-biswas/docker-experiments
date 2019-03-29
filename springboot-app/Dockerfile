FROM maven:3.3-jdk-8

WORKDIR springboot-app

COPY src /springboot-app/src/

COPY pom.xml /springboot-app/

RUN mvn clean package

WORKDIR /springboot-app/target/

EXPOSE 8080

CMD ["java","-jar","api-test-1.0.0-SNAPSHOT.jar"]