package com.railway.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.railway.manager"})
@EnableSwagger2
public class RailwayManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailwayManagerApplication.class, args);
    }

}
