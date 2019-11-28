package com.jasonlin.listener;

import com.jasonlin.service.SwaggerService;
import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@EnableBinding(Sink.class)
public class EurekaRegisterEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(EurekaRegisterEventListener.class);
    private final static Set IgnoreClient = new HashSet<>(Arrays.asList("eureka-service", "gateway-service"));
    @Autowired
    private SwaggerService swaggerService;

    @StreamListener(Sink.INPUT)
    private void doSomething(InstanceInfo instanceInfo) {

        // we can't get port by getPort()
        // because port has annotation of @JsonIgnore
        // and will not be serialized when sending message from eureka server
        // so I try to get it from Instance Id
        // instance id is just like : job-service:192.168.1.1:8020
        int port = Integer.parseInt(instanceInfo.getInstanceId().split(":")[2]);
        DefaultServiceInstance defaultServiceInstance = new DefaultServiceInstance(
                instanceInfo.getInstanceId(),
                instanceInfo.getAppName().toLowerCase(),
                instanceInfo.getHostName(),
                port,
                false);
        if (IgnoreClient.contains(instanceInfo.getAppName().toLowerCase())) {
            LOGGER.info("Receive Instance Info {}", instanceInfo.getAppName());
            swaggerService.fetchDocument(defaultServiceInstance);
        }
    }
}
