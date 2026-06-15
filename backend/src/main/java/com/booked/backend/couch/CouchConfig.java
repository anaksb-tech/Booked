package com.booked.backend.couch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CouchConfig {

    @Value("${couchdb.url}")
    private String url;

    @Value("${couchdb.username}")
    private String username;

    @Value("${couchdb.password}")
    private String senha;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, senha));
        return restTemplate;
    }
}
