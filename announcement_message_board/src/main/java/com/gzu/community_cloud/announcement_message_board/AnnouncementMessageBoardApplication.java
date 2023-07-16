package com.gzu.community_cloud.announcement_message_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AnnouncementMessageBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnouncementMessageBoardApplication.class, args);
    }

}
