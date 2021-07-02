package com.example.springdemoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaRepositories("com.example.springdemoproject")
//@EntityScan("com.example.springdemoproject.entities")
@SpringBootApplication
@EnableJpaAuditing
public class SpringDemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoProjectApplication.class, args);
    }

}
