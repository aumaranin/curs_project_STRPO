FROM ubuntu:20.04

RUN apt-get update

#Установка java
RUN apt install openjdk-17-jdk openjdk-17-jre -y

#установка maven
ADD https://dlcdn.apache.org/maven/maven-3/3.9.0/binaries/apache-maven-3.9.0-bin.tar.gz .
RUN tar -xvf apache-maven-3.9.0-bin.tar.gz && rm apache-maven-3.9.0-bin.tar.gz
RUN cp -r apache-maven-3.9.0 /opt
ENV PATH="/opt/apache-maven-3.9.0/bin:$PATH"
RUN echo "export PATH=$PATH:/etc/profile" >> ~/.bashrc

WORKDIR /app

COPY . .

#создаем JAR-архив
RUN mvn package

#запускаем приложение
CMD [ "java", "-jar", "./target/customerms.jar" ]