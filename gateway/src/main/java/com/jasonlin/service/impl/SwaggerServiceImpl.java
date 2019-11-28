package com.jasonlin.service.impl;

import com.jasonlin.service.SwaggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SwaggerServiceImpl implements SwaggerService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SwaggerServiceImpl.class);

    private final static String API_DOC = "/v2/api-docs";

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String fetchDocument(ServiceInstance serviceInstance) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                serviceInstance.getUri() + API_DOC, String.class
        );
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RemoteAccessException("get document failed : " + responseEntity);
        }
        String body = responseEntity.getBody();
        LOGGER.info(body);
        return body;
    }
}
