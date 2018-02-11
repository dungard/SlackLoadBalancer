package com.dungard.slackloadbalancer.implementations.bases;

import com.dungard.slackloadbalancer.interfaces.RpcHandler;
import org.apache.commons.validator.routines.UrlValidator;

/**
 * Base validator which has
 * functions for things like
 * url validation
 */
public abstract class BaseValidator implements RpcHandler {
    private UrlValidator httpValidator;
    private UrlValidator websocketValidator;

    protected boolean validHttpUrl(String url) {
        if (httpValidator == null) {
            httpValidator = new UrlValidator(new String[]{"http", "https"});
        }
        return httpValidator.isValid(url);
    }

    protected boolean validWebsocketUrl(String url) {
        if (websocketValidator == null) {
            websocketValidator = new UrlValidator(new String[]{"ws", "wss"});
        }
        return websocketValidator.isValid(url);
    }
}
