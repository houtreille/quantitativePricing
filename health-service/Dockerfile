FROM openjdk:11
LABEL authors="mhoutreille"
MAINTAINER eblood
COPY target/health-service-0.0.1-SNAPSHOT.jar health-service.jar
ENTRYPOINT ["java","-jar","/health-service.jar"]