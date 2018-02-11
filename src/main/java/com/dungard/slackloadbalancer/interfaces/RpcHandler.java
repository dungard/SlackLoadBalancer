package com.dungard.slackloadbalancer.interfaces;

import com.dungard.slackloadbalancer.config.SLBException;
import com.google.gson.JsonObject;

/**
 * Handles Rpc messages in chain
 */
public interface RpcHandler {
    String handleRpcRequest(JsonObject parameters) throws SLBException;
}
