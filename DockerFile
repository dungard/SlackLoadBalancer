FROM openjdk:8

COPY target/slackloadbalancer.jar /usr/src/slackloadbalancer.jar
COPY startbalancer.sh /

RUN apt-get update
RUN apt-get install -y netcat

CMD chmod +x /start.sh