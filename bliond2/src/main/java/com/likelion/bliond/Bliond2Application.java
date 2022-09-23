package com.likelion.bliond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Bliond2Application {

    public static void main(String[] args) {
        SpringApplication.run(Bliond2Application.class, args);
    }

}
