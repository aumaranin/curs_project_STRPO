FROM ubuntu:20.04

RUN apt-get update

RUN apt-get install default-jre default-jdk maven -y

WORKDIR /app

COPY . .

CMD [ "mvn", "compile", "exec:java" ]