package com.dungard.slackloadbalancer.models.parsers;

import com.dungard.slackloadbalancer.models.RpcMessage;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Singleton;

/**
 * Parses the JSON from RPC
 * request and turns it into
 * RpcMessage object
 */
@Singleton
public class RpcMessageParser {

    public RpcMessage parseRpcMessage(String json) {
        return parseRpcMessage(new JsonParser().parse(json).getAsJsonObject());
    }

    public RpcMessage parseRpcMessage(JsonObject jsonObject) {
        RpcMessage rpcMessage = new RpcMessage();

        rpcMessage.setType(jsonObject.get("type").getAsString());
        rpcMessage.setParameters(jsonObject.get("parameters").getAsJsonObject());

        return rpcMessage;
    }
}
