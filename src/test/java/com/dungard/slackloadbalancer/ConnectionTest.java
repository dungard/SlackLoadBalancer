package com.dungard.slackloadbalancer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by William Dunne on 19/01/2018.
 */
public class ConnectionTest {

    @Test
    public void testConnection() throws IOException, TimeoutException {
        String host = System.getenv("RABBIT_MASTER");
        System.out.println("Connecting to... " + host);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
    }
}
