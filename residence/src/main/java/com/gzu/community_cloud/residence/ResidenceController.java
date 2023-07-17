package com.gzu.community_cloud.residence;


import com.gzu.community_cloud.residence.feign.AnnouncementMessageBoardFeign;
import com.gzu.community_cloud.residence.feign.UnitRoomFeign;
import com.gzu.community_cloud.residence.pojo.Residence;
import com.gzu.community_cloud.residence.pojo.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ResidenceController {
    ResidenceService residenceService;
    UnitRoomFeign unitRoomFeign;
    AnnouncementMessageBoardFeign announcementMessageBoardFeign;

    public ResidenceController(ResidenceService residenceService, UnitRoomFeign unitRoomFeign, AnnouncementMessageBoardFeign announcementMessageBoardFeign) {
        this.residenceService = residenceService;
        this.unitRoomFeign = unitRoomFeign;
        this.announcementMessageBoardFeign = announcementMessageBoardFeign;
    }

    @GetMapping("/feign/login")
    @ResponseBody
    String feignLogin(@RequestParam String username, @RequestParam String password) {
        return residenceService.login(username, password).toString();
    }

    @GetMapping("/feign/register")
    @ResponseBody
    String feignRegister(@RequestParam String username, @RequestParam String password,
                         @RequestParam String name, @RequestParam String unitNumber,
                         @RequestParam String roomNumber) {
        return residenceService.register(username, password, name, unitNumber, roomNumber).toString();
    }

    @GetMapping("/feign/get-residence")
    @ResponseBody
    Residence getResidence(@RequestParam String username) {
        return residenceService.getResidence(username);
    }

    @GetMapping("/feign/get-residences-by-unit-number-and-room-number")
    @ResponseBody
    ArrayList<Residence> getResidencesByUnitNumberAndRoomNumber(String unitNumber, String roomNumber) {
        return residenceService.getResidencesByUnitNumberAndRoomNumber(unitNumber, roomNumber);
    }

    @GetMapping("/residence")
    public String index() {
        return "forward:/residence/residence.html";
    }

    @GetMapping("/residence/info")
    @ResponseBody
    public Residence info(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        return residenceService.getResidence(username);
    }

    @GetMapping("/residence/room-info")
    @ResponseBody
    public Room roomInfo(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        Residence residence = residenceService.getResidence(username);
        Room room = unitRoomFeign.getRoom(
                residence.getUnitNumber(), residence.getRoomNumber()
        );
        if (room.getHouseHolder() != null) {
            Residence houseHolder = residenceService.getResidence(room.getHouseHolder());
            room.setHouseHolder(houseHolder.getName());
        }
        return room;
    }

    @GetMapping("/residence/family-info")
    @ResponseBody
    public ArrayList<Residence> familyInfo(HttpServletRequest httpServletRequest) {
        String username = (String) httpServletRequest.getAttribute("username");
        Residence residence = residenceService.getResidence(username);
        return residenceService.getResidencesByUnitNumberAndRoomNumber(
                residence.getUnitNumber(), residence.getRoomNumber()
        );
    }

    @GetMapping("/residence/set-house-holder")
    public String setHouseHolder(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        String username = (String) httpServletRequest.getAttribute("username");
        Residence residence = residenceService.getResidence(username);
        int setHouseHolderStatus = Integer.parseInt(unitRoomFeign.setHouseHolder(
                residence.getUnitNumber(), residence.getRoomNumber(), username
        ));
        switch (setHouseHolderStatus) {
            case 0:
                redirectAttributes.addAttribute("error", "成功设置户主");
                break;
            case 1:
                redirectAttributes.addAttribute("error", "未设置户主");
                break;
            case 2:
                redirectAttributes.addAttribute("error", "发生错误");
                break;
        }
        return "redirect:/residence";
    }

    @PostMapping("/residence/commit-message-board")
    public String commitMessageBoard(@RequestParam("content") String content, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        String username = (String) httpServletRequest.getAttribute("username");
        int commitMessageBoardStatus = Integer.parseInt(announcementMessageBoardFeign.commitMessageBoard(username, content));
        if (commitMessageBoardStatus == 0) {
            redirectAttributes.addAttribute("error", "成功提交留言");
        } else {
            redirectAttributes.addAttribute("error", "提交留言失败");
        }
        return "redirect:/residence";
    }
}
