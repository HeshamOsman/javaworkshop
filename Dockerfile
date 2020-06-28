From ubuntu:bionic
MAINTAINER HeshamOsman

RUN apt-get update && apt-get -y upgrade

RUN apt-get install -y openjdk-11-jdk

WORKDIR ~/countryservice

ARG appver=0.0.1-SNAPSHOT
ENV appver=${appver}
EXPOSE 8080
COPY ./target/countryservice-${appver}.jar .

CMD exec java -jar countryservice-${appver}.jar

