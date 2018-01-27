package com.dungard.slackloadbalancer;

import org.junit.AfterClass;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Base test, spins up websocket server and
 * rabbitmq client
 */
public abstract class BaseTest {
    protected TestWebsocketServer testWebsocketServer;
    protected TestRabbitClient testRabbitClient;

    public BaseTest() throws IOException, TimeoutException {
        testWebsocketServer = new TestWebsocketServer();
        testRabbitClient = new TestRabbitClient();
    }

    @AfterClass
    public void cleanClass() throws IOException, InterruptedException {
        testWebsocketServer.stop();
        testRabbitClient.shutDown();
    }
}
