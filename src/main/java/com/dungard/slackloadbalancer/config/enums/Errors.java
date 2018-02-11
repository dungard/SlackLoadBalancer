package com.dungard.slackloadbalancer.config.enums;

/**
 * Various error values
 */
public enum Errors {
    VALIDATION("validation"),
    VALIDATION_MISSING_FIELD_MESSAGE("The {0} field must be provided"),
    VALIDATION_INVALID_FIELD_MESSAGE("The {0} field is invalid");

    private String value;

    Errors(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
