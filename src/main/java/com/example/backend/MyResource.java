package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyResource {

    @Autowired
    ApplicationContext context;

    @GetMapping("hello")
    public Personne hello() {
        return Personne.builder()
                .nom("test")
                .prenom("test")
                .build();
    }

}

