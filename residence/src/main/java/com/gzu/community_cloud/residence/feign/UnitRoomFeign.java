package com.gzu.community_cloud.residence.feign;

import com.gzu.community_cloud.residence.pojo.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("unit-room")
public interface UnitRoomFeign {
    @GetMapping("/feign/get-room")
    Room getRoom(@RequestParam String unitNumber, @RequestParam String roomNumber);

    @GetMapping("/feign/set-householder")
    String setHouseHolder(@RequestParam String unitNumber, @RequestParam String roomNumber,
                          @RequestParam String username);
}
