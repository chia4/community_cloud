package com.gzu.community_cloud.unit_room;


import com.gzu.community_cloud.unit_room.pojo.Room;
import com.gzu.community_cloud.unit_room.pojo.Unit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class UnitRoomController {
    UnitRoomService unitRoomService;

    public UnitRoomController(UnitRoomService unitRoomService) {
        this.unitRoomService = unitRoomService;
    }

    @GetMapping("/feign/get-room")
    @ResponseBody
    Room getRoom(@RequestParam String unitNumber, @RequestParam String roomNumber) {
        return unitRoomService.getRoom(unitNumber, roomNumber);
    }

    @GetMapping("/feign/set-householder")
    @ResponseBody
    String setHouseHolder(@RequestParam String unitNumber, @RequestParam String roomNumber,
                          @RequestParam String username) {
        return unitRoomService.setHouseHolder(unitNumber, roomNumber, username).toString();
    }

    @GetMapping("/feign/delete-unit")
    @ResponseBody
    String deleteUnit(@RequestParam String unitNumber) {
        return unitRoomService.deleteUnit(unitNumber).toString();
    }

    @GetMapping("/feign/delete-room")
    @ResponseBody
    String deleteRoom(@RequestParam String unitNumber, @RequestParam String roomNumber) {
        return unitRoomService.deleteRoom(unitNumber, roomNumber).toString();
    }

    @GetMapping("/feign/insert-unit")
    @ResponseBody
    String insertUnit(@RequestParam String unitNumber, @RequestParam String caretaker, @RequestParam String cleaner) {
        return unitRoomService.insertUnit(unitNumber, caretaker, cleaner).toString();
    }

    @GetMapping("/feign/insert-room")
    @ResponseBody
    String insertRoom(@RequestParam String unitNumber, @RequestParam String roomNumber, @RequestParam String houseHolder) {
        if (houseHolder.equals("")) {
            houseHolder = null;
        }
        return unitRoomService.insertRoom(unitNumber, roomNumber, houseHolder).toString();
    }

    @GetMapping("/feign/get-unit")
    @ResponseBody
    Unit getUnit(@RequestParam String unitNumber) {
        return unitRoomService.getUnit(unitNumber);
    }

    @GetMapping("/feign/select-rooms-by-unit-number")
    @ResponseBody
    ArrayList<Room> selectRoomsByUnitNumber(@RequestParam String unitNumber) {
        return unitRoomService.selectRoomsByUnitNumber(unitNumber);
    }

    @GetMapping("/unit-room/get-units")
    @ResponseBody
    public ArrayList<String> getUnits() {
        ArrayList<String> unitNumbers = new ArrayList<>();
        ArrayList<Unit> units = unitRoomService.getUnits();
        for (Unit unit : units) {
            unitNumbers.add(unit.getUnitNumber());
        }
        return unitNumbers;
    }

    @GetMapping("/unit-room/get-rooms")
    @ResponseBody
    public HashMap<String, ArrayList<String>> getRooms() {
        HashMap<String, ArrayList<String>> unitNumberRoomNumbersMap = new HashMap<>();
        ArrayList<Room> rooms = unitRoomService.getRooms();
        for (Room room : rooms) {
            String unitNumber = room.getUnitNumber();
            ArrayList<String> roomNumbers = unitNumberRoomNumbersMap.get(unitNumber);
            if (roomNumbers == null) {
                roomNumbers = new ArrayList<>();
            }
            roomNumbers.add(room.getRoomNumber());
            unitNumberRoomNumbersMap.put(unitNumber, roomNumbers);
        }
        return unitNumberRoomNumbersMap;
    }
}
