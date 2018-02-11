package com.dungard.slackloadbalancer.config;

import com.dungard.slackloadbalancer.implementations.RpcListenerImpl;
import com.dungard.slackloadbalancer.interfaces.RpcListener;
import com.google.inject.AbstractModule;

/**
 * Module to set up Guice DI
 */
public class SlackLoadBalancerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RpcListener.class).to(RpcListenerImpl.class);
    }
}
