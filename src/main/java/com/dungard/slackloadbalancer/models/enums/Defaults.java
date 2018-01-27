package com.dungard.slackloadbalancer.models.enums;

/**
 * Default values for various things
 */
public enum Defaults {
    RPC_QUEUE_NAME("rpc_queue");

    private String value;

    private Defaults(String value) {
        this.value = value;
    }
}
