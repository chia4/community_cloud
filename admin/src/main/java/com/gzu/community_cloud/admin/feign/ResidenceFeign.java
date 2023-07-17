package com.gzu.community_cloud.admin.feign;

import com.gzu.community_cloud.admin.pojo.Residence;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@FeignClient("residence")
public interface ResidenceFeign {
    @GetMapping("/feign/get-residence")
    Residence getResidence(@RequestParam String username);

    @GetMapping("/feign/get-residences-by-unit-number-and-room-number")
    ArrayList<Residence> getResidencesByUnitNumberAndRoomNumber(@RequestParam String unitNumber, @RequestParam String roomNumber);
}
