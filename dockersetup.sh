#!/bin/bash

mvn clean package

docker image build -t slackloadbalancer:latest .

docker stack deploy --compose-file=docker-compose.yml slackbalancercluster