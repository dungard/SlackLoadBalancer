package com.dungard.slackloadbalancer.models.parsers;

import com.dungard.slackloadbalancer.models.RpcMessage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the RpcMessageParser
 */
public class RpcMessageParserTest {
    private RpcMessageParser subject = new RpcMessageParser();

    @Test
    public void populatesTypeField() {
        RpcMessage rpcMessage = subject.parseRpcMessage(
            "{\n \"type\": \"connect\",\n \"parameters\": {}\n}"
        );

        Assert.assertEquals("connect", rpcMessage.getType());
    }

    @Test
    public void populatesParameterField() {

    }
}
