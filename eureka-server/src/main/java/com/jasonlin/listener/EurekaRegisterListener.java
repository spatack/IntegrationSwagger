package com.jasonlin.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.support.MessageBuilder;


@EnableBinding(Source.class)
public class EurekaRegisterListener implements ApplicationListener<EurekaInstanceRegisteredEvent> {

    private final static Logger LOGGER = LoggerFactory.getLogger(EurekaRegisterListener.class);

    @Autowired
    private  Source source;

    @Override
    public void onApplicationEvent(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent) {
        LOGGER.info("Eureka Client Registered");
        if (eurekaInstanceRegisteredEvent.isReplication()) {
            source.output().send ((MessageBuilder.withPayload(eurekaInstanceRegisteredEvent.getInstanceInfo()).build()));
        }
    }
}
