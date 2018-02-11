package com.dungard.slackloadbalancer.implementations.connect;

import com.dungard.slackloadbalancer.config.SLBException;
import com.dungard.slackloadbalancer.config.enums.Errors;
import com.dungard.slackloadbalancer.implementations.bases.BaseValidator;
import com.dungard.slackloadbalancer.interfaces.RpcHandler;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Ensures that has necessary fields
 */
@Singleton
public class ValidateConnect extends BaseValidator {
    private RpcHandler nextHandler;

    @Inject
    public ValidateConnect(HandleConnect handleConnect) {
        this.nextHandler = handleConnect;
    }

    @Override
    public String handleRpcRequest(JsonObject parameters) throws SLBException {
        validateUrl(parameters);
        return nextHandler.handleRpcRequest(parameters);
    }

    private void validateUrl(JsonObject parameters) throws SLBException {
        String errorMessage = null;
        if (parameters.has("url")) {
            if (!validWebsocketUrl(parameters.get("url").getAsString())) {
                errorMessage = String.format(Errors.VALIDATION_INVALID_FIELD_MESSAGE.toString(), "URL");
            }
        } else {
            errorMessage = String.format(Errors.VALIDATION_MISSING_FIELD_MESSAGE.toString(), "URL");
        }

        if (errorMessage != null) {
            throw new SLBException(Errors.VALIDATION.toString(), errorMessage);
        }
    }
}
