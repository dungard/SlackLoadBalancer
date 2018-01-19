#!/bin/bash

echo $RABBIT_MASTER

while ! nc -z $RABBIT_MASTER 5672; do sleep 3; done

java -jar /usr/src/slackloadbalancer.jar