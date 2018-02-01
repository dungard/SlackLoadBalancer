package com.dungard.slackloadbalancer.rabbitmq;

import com.dungard.slackloadbalancer.interfaces.RabbitHandler;
import com.dungard.slackloadbalancer.interfaces.RabbitRpcHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Listens to queues and does other
 * servery things
 */
@Singleton
public class RabbitServer {
    @Inject
    private RabbitConnection rabbitConnection;

    public void addListenerToRpcQueue(String queueName, RabbitRpcHandler rpcHandler) throws IOException {
        Channel channel = rabbitConnection.getChannel();

        channel.queueDeclare(queueName,false, false, false, null);

        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(properties.getCorrelationId())
                        .build();


                String message = new String(body,"UTF-8");

                String response = rpcHandler.handleRpcRequest(message);

                channel.basicPublish( "", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
                channel.basicAck(envelope.getDeliveryTag(), false);
                // RabbitMq consumer worker thread notifies the RPC server owner thread
                synchronized(this) {
                    this.notify();
                }
            }
        };

        channel.basicConsume(queueName, false, consumer);
    }

    public void addListenerToQueue(String queueName, RabbitHandler rabbitHandler) throws IOException {
        Channel channel = rabbitConnection.getChannel();

        channel.queueDeclare(queueName,false, false, false, null);

        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");

                rabbitHandler.handleRequest(message);
            }
        };

        channel.basicConsume(queueName, true, consumer);
    }
}
