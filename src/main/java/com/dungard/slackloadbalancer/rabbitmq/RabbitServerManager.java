package com.dungard.slackloadbalancer.rabbitmq;

import com.dungard.slackloadbalancer.config.enums.Defaults;
import com.dungard.slackloadbalancer.interfaces.RpcListener;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;

/**
 * Creates the RabbitServer and adds the
 * base listeners to it
 */
@Singleton
public class RabbitServerManager {
    @Inject
    private RabbitServer rabbitServer;
    @Inject
    private RpcListener rpcListener;

    public void setUpRabbitServer() throws IOException {
        rabbitServer.addListenerToRpcQueue(Defaults.RPC_QUEUE_NAME.toString(), rpcListener);
    }
}
