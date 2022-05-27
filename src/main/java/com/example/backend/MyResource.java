package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Profile("!prod")
public class MyResource {

    @Value("${env}")
    String env;

    private final ApplicationContext context;


    public MyResource(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("hello")
    public Personne hello() {
        return Personne.builder()
                .nom("integration v.4")
                .prenom("integration v.4")
                .env(env)
                .build();
    }

}

