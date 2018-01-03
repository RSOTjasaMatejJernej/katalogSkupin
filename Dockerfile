FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./target/katalogSkupin-1.0.0.jar /app

EXPOSE 8081

CMD ["java", "-jar", "katalogSkupin-1.0.0.jar"]