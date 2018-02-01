package com.dungard.slackloadbalancer;

import org.junit.AfterClass;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Base test, spins up websocket server and
 * rabbitmq client
 */
public abstract class BaseTest {
    protected static TestWebsocketServer testWebsocketServer;
    protected static TestRabbitClient testRabbitClient;

    public BaseTest() throws IOException, TimeoutException {
        testWebsocketServer = new TestWebsocketServer();
        testRabbitClient = new TestRabbitClient();
    }

    @AfterClass
    public static void cleanClass() throws IOException, InterruptedException {
        testWebsocketServer.stop();
        testRabbitClient.shutDown();
    }
}
