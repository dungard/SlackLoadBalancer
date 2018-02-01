package com.dungard.slackloadbalancer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ client for test purposes
 */
public class TestRabbitClient {
    private RpcClient rpcClient;
    private Connection connection;
    private Channel channel;
    private final String RPC_QUEUE = "rpc_queue";
    private String returnQueue;
    private Map<String, List<String>> receivedMessages;

    public TestRabbitClient() throws IOException, TimeoutException {
        String host = System.getenv("RABBIT_MASTER");
        System.out.println("Connecting to... " + host);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(5672);

        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(RPC_QUEUE, false, false, false, null);
        returnQueue = channel.queueDeclare().getQueue();

        receivedMessages = new HashMap<>();
    }

    public String sendRpc(String message) throws IOException, InterruptedException {
        String corrId = UUID.randomUUID().toString();

        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(returnQueue)
                .build();

        channel.basicPublish("", RPC_QUEUE, props, message.getBytes("UTF-8"));

        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

        channel.basicConsume(returnQueue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(corrId)) {
                    response.offer(new String(body, "UTF-8"));
                }
            }
        });

        return response.poll(2, TimeUnit.SECONDS);
    }

    public void subscribeToQueue(String queueName) throws IOException {
        channel.basicConsume(queueName, true, (tag, message) -> {
            if (!receivedMessages.containsKey(queueName)) {
                receivedMessages.put(queueName, new ArrayList<>());
            }
            receivedMessages.get(queueName).add(new String(message.getBody(), "UTF-8"));
        }, (tag, exception) -> {});
    }

    public List<String> getMessages(String queueName) {
        return receivedMessages.get(queueName);
    }

    public void shutDown() throws IOException {
        receivedMessages = new HashMap<>();
        connection.close();
    }
}
