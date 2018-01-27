package com.dungard.slackloadbalancer.rabbitmq;

import com.google.inject.Singleton;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Creates and holds the RabbitMQ
 * connection for usage
 */
@Singleton
public class RabbitConnection {
    private Connection connection;
    private Channel channel;

    public RabbitConnection() throws IOException, TimeoutException {
        String host = System.getenv("RABBIT_MASTER");
        System.out.println("Connecting to... " + host);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(5672);
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public Connection getConnection() {
        return connection;
    }

    public Channel getChannel() {
        return channel;
    }
}
