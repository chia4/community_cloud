package com.gzu.community_cloud.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("authentication")
public interface AuthenticationFeign {
    @GetMapping("/feign/is-admin")
    String isAdmin(@RequestHeader("Cookie") String cookie);
}
