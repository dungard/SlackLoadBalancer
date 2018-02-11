package com.dungard.slackloadbalancer;

import com.dungard.slackloadbalancer.config.SlackLoadBalancerModule;
import com.dungard.slackloadbalancer.rabbitmq.RabbitServerManager;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class App {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SlackLoadBalancerModule());
        RabbitServerManager rabbitServerManager = injector.getInstance(RabbitServerManager.class);
    }
}
