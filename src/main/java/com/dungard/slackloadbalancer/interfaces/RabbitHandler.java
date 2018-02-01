package com.dungard.slackloadbalancer.interfaces;

/**
 * Handles requests that don't
 * need listening to
 */
public interface RabbitHandler {
    void handleRequest(String message);
}
