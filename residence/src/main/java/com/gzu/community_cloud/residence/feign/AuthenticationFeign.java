package com.gzu.community_cloud.residence.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("authentication")
public interface AuthenticationFeign {
    @GetMapping("/feign/get-username")
    String getUsername(@RequestHeader("Cookie") String cookie);
}
