FROM maven:3.6.3-openjdk-11
LABEL maintainer="hesham.osman28@gmail.com"

ADD . ~/countryservice

ENV hostip=

EXPOSE 8080

WORKDIR ~/countryservice

RUN mvn package -Pprod -DskipTests

WORKDIR ./target

ENV activeProf=prod
ENV dpport=

CMD exec java -DactiveProf=${activeProf} -Ddpport=${dpport}  -Dhostip=${hostip} -jar countryservice-0.0.1-SNAPSHOT.jar

