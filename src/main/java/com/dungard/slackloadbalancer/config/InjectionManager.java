package com.dungard.slackloadbalancer.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * Handles all injections
 */
@Singleton
public class InjectionManager {
    private Injector injector = Guice.createInjector(new SlackLoadBalancerModule());

    public void inject(Object patient) {
        injector.injectMembers(patient);
    }

    public <T> T getInstance(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    public Injector getInjector() {
        return injector;
    }
}
