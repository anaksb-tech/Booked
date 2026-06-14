package com.booked.backend.couch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchConfig {

    @Value("${couchdb.url}")
    private String url;

    @Value("${couchdb.username}")
    private String username;

    @Value("${couchdb.password}")
    private String senha;

}
