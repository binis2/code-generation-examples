package net.binis.example.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ComponentScan(basePackages = {"net.binis"})
@EntityScan(basePackages = {"net.binis.example.db.entity"})
@SpringBootApplication
@EnableJpaAuditing
public class AnnotationExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnotationExampleApplication.class, args);
    }

}
