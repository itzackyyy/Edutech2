package com.edutech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.edutech","com.edutech.Assemblers"})
public class EdutechInnovationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdutechInnovationApplication.class, args);
    }

}
