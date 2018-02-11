package com.dungard.slackloadbalancer.implementations;

import com.dungard.slackloadbalancer.config.InjectionManager;
import com.dungard.slackloadbalancer.config.SLBException;
import com.dungard.slackloadbalancer.config.enums.Errors;
import com.dungard.slackloadbalancer.implementations.connect.ValidateConnect;
import com.dungard.slackloadbalancer.interfaces.RpcHandler;
import com.dungard.slackloadbalancer.interfaces.RpcListener;
import com.dungard.slackloadbalancer.models.ExceptionResponse;
import com.dungard.slackloadbalancer.models.RpcMessage;
import com.dungard.slackloadbalancer.models.parsers.RpcMessageParser;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Default handler for Rpc message
 */
@Singleton
public class RpcListenerImpl implements RpcListener {
    @Inject
    private RpcMessageParser rpcMessageParser;
    @Inject
    private InjectionManager injectionManager;
    private Map<String, RpcHandler> handlers = new HashMap<>();
    private Gson gson = new Gson();

    @Override
    public String handleRpcRequest(String request) {
        String response;
        RpcMessage rpcMessage = rpcMessageParser.parseRpcMessage(request);
        try {
            RpcHandler rpcHandler = getRpcHandler(rpcMessage.getType());
            response = rpcHandler.handleRpcRequest(rpcMessage.getParameters());
        } catch (SLBException e) {
            response = gson.toJson(new ExceptionResponse(e.getName(), e.getDescription()));
        } catch (Exception e) {
            response = gson.toJson(new ExceptionResponse("unknown", "An unknown error occurred"));
        }
        return response;
    }

    private RpcHandler getRpcHandler(String requestType) throws SLBException {
        RpcHandler rpcHandler = null;
        if (handlers.containsKey(requestType)) {
            rpcHandler = handlers.get(requestType);
        } else {
            switch (requestType) {
                case "connect":
                    rpcHandler = injectionManager.getInstance(ValidateConnect.class);
                    break;
                default:
                    break;
            }
            if (rpcHandler != null) {
                handlers.put(requestType, rpcHandler);
            } else {
                throw new SLBException("validation",
                        String.format(Errors.VALIDATION_INVALID_FIELD_MESSAGE.toString(), "type"));
            }
        }
        return rpcHandler;
    }
}
