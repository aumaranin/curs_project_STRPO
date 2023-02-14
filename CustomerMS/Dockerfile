FROM ubuntu:20.04

RUN apt-get update

RUN apt-get install default-jre default-jdk maven -y
#RUN apt install openjdk-17-jdk openjdk-17-jre maven -y

WORKDIR /app

COPY . .

#CMD [ "mvn", "compile", "exec:java" ]
CMD [ "mvn", "clean", "compile", "exec:java" ]