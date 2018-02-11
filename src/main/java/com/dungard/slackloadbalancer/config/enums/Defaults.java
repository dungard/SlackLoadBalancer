package com.dungard.slackloadbalancer.config.enums;

/**
 * Default values for various things
 */
public enum Defaults {
    RPC_QUEUE_NAME("rpc_queue");

    private String value;

    Defaults(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
