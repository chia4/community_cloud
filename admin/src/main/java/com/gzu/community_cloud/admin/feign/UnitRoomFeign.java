package com.gzu.community_cloud.admin.feign;


import com.gzu.community_cloud.admin.pojo.Room;
import com.gzu.community_cloud.admin.pojo.Unit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@FeignClient("unit-room")
public interface UnitRoomFeign {
    @GetMapping("/feign/delete-unit")
    String deleteUnit(@RequestParam String unitNumber);

    @GetMapping("/feign/delete-room")
    String deleteRoom(@RequestParam String unitNumber, @RequestParam String roomNumber);

    @GetMapping("/feign/insert-unit")
    String insertUnit(@RequestParam String unitNumber, @RequestParam String caretaker, @RequestParam String cleaner);

    @GetMapping("/feign/insert-room")
    String insertRoom(@RequestParam String unitNumber, @RequestParam String roomNumber, @RequestParam String houseHolder);

    @GetMapping("/feign/get-room")
    Room getRoom(@RequestParam String unitNumber, @RequestParam String roomNumber);

    @GetMapping("/feign/get-unit")
    Unit getUnit(@RequestParam String unitNumber);

    @GetMapping("/feign/select-rooms-by-unit-number")
    ArrayList<Room> selectRoomsByUnitNumber(@RequestParam String unitNumber);
}
