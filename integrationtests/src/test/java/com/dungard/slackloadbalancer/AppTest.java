package com.dungard.slackloadbalancer;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Integration tests for complete Swarm setup
 * and load balancer project
 */
public class AppTest extends BaseTest {
    public AppTest() throws IOException, TimeoutException { }

    @Test
    public void testLogin() throws IOException, InterruptedException {
        testRabbitClient.sendRpc("{\n" +
                "  \"type\": \"connect\",\n" +
                "  \"token\": \"99dd4a28-eb49-46da-ab25-cc6a9f6a4d38\"\n" +
                "}");
        Thread.sleep(10);
        Assert.assertTrue(testWebsocketServer.getJoined() > 0);
    }
}
