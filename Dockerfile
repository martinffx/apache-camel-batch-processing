FROM openjdk:8-alpine

WORKDIR /usr/src
COPY ./ /usr/src
RUN ./gradlew stage

CMD ["./build/install/apache-camel-batch-processing/bin/apache-camel-batch-processing"]
