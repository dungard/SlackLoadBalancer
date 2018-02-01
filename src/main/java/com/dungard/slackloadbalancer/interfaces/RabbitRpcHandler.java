package com.dungard.slackloadbalancer.interfaces;

/**
 * Single method interface for
 * handling
 */
public interface RabbitRpcHandler {
    String handleRpcRequest(String request);
}
