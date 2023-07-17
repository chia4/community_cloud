package com.gzu.community_cloud.authentication.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("residence")
public interface ResidenceFeign {
    @GetMapping("/feign/login")
    String login(@RequestParam String username, @RequestParam String password);

    @GetMapping("/feign/register")
    String register(@RequestParam String username, @RequestParam String password,
                    @RequestParam String name, @RequestParam String unitNumber,
                    @RequestParam String roomNumber);
}
