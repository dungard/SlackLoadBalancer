package com.dungard.slackloadbalancer.implementations.connect;

import com.dungard.slackloadbalancer.config.SLBException;
import com.dungard.slackloadbalancer.interfaces.RpcHandler;
import com.google.gson.JsonObject;
import com.google.inject.Singleton;

/**
 * Handles the connection to Slack
 */
@Singleton
public class HandleConnect implements RpcHandler {
    @Override
    public String handleRpcRequest(JsonObject parameters) throws SLBException {
        return null;
    }
}
