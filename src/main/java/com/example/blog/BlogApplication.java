package com.example.blog;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("com.example.api")
@MapperScan("com.example.mapping")
@ComponentScan("com.example.api")
@ComponentScan("com.example.mapping")

public class BlogApplication {

    public static void main(String[] args) {

        SpringApplication.run(BlogApplication.class, args);
    }

}
