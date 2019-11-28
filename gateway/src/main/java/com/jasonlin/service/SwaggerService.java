package com.jasonlin.service;

import org.springframework.cloud.client.ServiceInstance;

public interface SwaggerService {

    String fetchDocument(ServiceInstance serviceInstance);
}
