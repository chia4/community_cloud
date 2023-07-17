package com.gzu.community_cloud.announcement_message_board.feign;

import com.gzu.community_cloud.announcement_message_board.pojo.Residence;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("residence")
public interface ResidenceFeign {
    @GetMapping("/feign/get-residence")
    Residence getResidence(@RequestParam String username);
}
