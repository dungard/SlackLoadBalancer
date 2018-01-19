package com.dungard.slackloadbalancer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class App {

    public static void main(String[] args) {
        try {
            String host = System.getenv("RABBIT_MASTER");
            System.out.println("Connecting to... " + host);
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setPort(5672);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            System.out.println("=====================");
            System.out.println("    Big Success");
            System.out.println("=====================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
