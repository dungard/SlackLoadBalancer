#!/bin/bash

docker run -d -p 5672:5672 --name rabbitmq harbur/rabbitmq-cluster

sleep 5

mvn clean package

docker container stop rabbitmq

docker image build -t slackloadbalancer:latest .

docker stack deploy --compose-file=docker-compose.yml slackbalancercluster