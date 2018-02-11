package com.dungard.slackloadbalancer.config;

/**
 * Exception to be used internally
 * which includes some fields
 */
public class SLBException extends Exception {
    private String name;
    private String description;

    public SLBException(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
