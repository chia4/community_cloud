package com.gzu.community_cloud.residence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ResidenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResidenceApplication.class, args);
    }

}
