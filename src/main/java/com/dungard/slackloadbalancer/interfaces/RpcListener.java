package com.dungard.slackloadbalancer.interfaces;

/**
 * Single method interface for
 * handling
 */
public interface RpcListener {
    String handleRpcRequest(String request);
}
