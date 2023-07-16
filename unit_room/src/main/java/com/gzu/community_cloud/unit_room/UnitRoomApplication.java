package com.gzu.community_cloud.unit_room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UnitRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitRoomApplication.class, args);
    }

}
