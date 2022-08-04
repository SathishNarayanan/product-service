#!/bin/bash
FROM gradle:jdk11 AS gradleimage

FROM --platform=linux/amd64 openjdk:11-jre-slim
ADD ./build/libs/product-service-1.0.0-SNAPSHOT.jar productservice.jar
ENTRYPOINT ["java","-jar","/productservice.jar"]