package com.dungard.slackloadbalancer.models;

/**
 * Response for when there is
 * an exception
 */
public class ExceptionResponse {
    private String exception;
    private String description;

    public ExceptionResponse() {}

    public ExceptionResponse(String exception, String description) {
        this.exception = exception;
        this.description = description;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
