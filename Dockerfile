FROM openjdk:8-alpine

WORKDIR /usr/src
COPY ./build/install/apache-camel-batch-processing /usr/src

CMD ["./bin/apache-camel-batch-processing"]
