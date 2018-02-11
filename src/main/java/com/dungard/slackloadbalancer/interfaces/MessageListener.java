package com.dungard.slackloadbalancer.interfaces;

/**
 * Handles requests that don't
 * need responding to
 */
public interface MessageListener {
    void handleRequest(String message);
}
