package com.example.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("prod")
public class MyResourceProd {

    @Value("${env}")
    String env;

    private final ApplicationContext context;

    public MyResourceProd(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("hello")
    public Personne hello() {
        return Personne.builder()
                .nom("production v1.0")
                .prenom("production v1.0")
                .env(env)
                .build();
    }

}
