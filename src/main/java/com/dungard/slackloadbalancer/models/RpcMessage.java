package com.dungard.slackloadbalancer.models;

import com.google.gson.JsonObject;

/**
 * Simple message class to be received
 * from RabbitMQ rpc channel
 */
public class RpcMessage {
    private String type;
    private JsonObject parameters;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonObject getParameters() {
        return parameters;
    }

    public void setParameters(JsonObject parameters) {
        this.parameters = parameters;
    }
}
