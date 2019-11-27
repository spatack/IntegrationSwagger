package com.jasonlin.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
public class EurekaRegisterEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(EurekaRegisterEventListener.class);

    @StreamListener(Sink.INPUT)
    private void doSomething(Message<String> message) {
        // TODO do something here
        LOGGER.info("Receive Message",message);
    }
}
