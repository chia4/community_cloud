package com.gzu.community_cloud.admin;


import com.gzu.community_cloud.admin.feign.AnnouncementMessageBoardFeign;
import com.gzu.community_cloud.admin.feign.ResidenceFeign;
import com.gzu.community_cloud.admin.feign.UnitRoomFeign;
import com.gzu.community_cloud.admin.pojo.Residence;
import com.gzu.community_cloud.admin.pojo.Room;
import com.gzu.community_cloud.admin.pojo.Unit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Controller
public class AdminController {
    AnnouncementMessageBoardFeign announcementMessageBoardFeign;
    UnitRoomFeign unitRoomFeign;
    ResidenceFeign residenceFeign;

    public AdminController(AnnouncementMessageBoardFeign announcementMessageBoardFeign, UnitRoomFeign unitRoomFeign,
                           ResidenceFeign residenceFeign) {
        this.announcementMessageBoardFeign = announcementMessageBoardFeign;
        this.unitRoomFeign = unitRoomFeign;
        this.residenceFeign = residenceFeign;
    }

    @GetMapping("/admin")
    public String index() {
        return "forward:/admin/admin.html";
    }

    @GetMapping("/admin/unit")
    public String unit(@RequestParam("type") String type, @RequestParam("unitNumber") String unitNumber,
                       RedirectAttributes redirectAttributes) {
        if (type.equals("查询单元")) {
            return "forward:/admin/unit-info.html";
        } else if (type.equals("删除单元")) {
            int deleteUnitStatus = Integer.parseInt(unitRoomFeign.deleteUnit(unitNumber));
            switch (deleteUnitStatus) {
                case 0:
                    redirectAttributes.addAttribute("error", "成功删除单元");
                    break;
                case 1:
                    redirectAttributes.addAttribute("error", "不存在该单元");
                    break;
                case 2:
                    redirectAttributes.addAttribute("error", "发生错误");
                    break;
            }
            return "redirect:/admin";
        }
        throw new RuntimeException("发生错误");
    }

    @GetMapping("/admin/room")
    public String room(@RequestParam("type") String type, @RequestParam("unitNumber") String unitNumber,
                       @RequestParam("roomNumber") String roomNumber, RedirectAttributes redirectAttributes) {
        if (type.equals("查询房间")) {
            return "forward:/admin/room-info.html";
        } else if (type.equals("删除房间")) {
            int deleteRoomStatus = Integer.parseInt(unitRoomFeign.deleteRoom(unitNumber, roomNumber));
            switch (deleteRoomStatus) {
                case 0:
                    redirectAttributes.addAttribute("error", "成功删除房间");
                    break;
                case 1:
                    redirectAttributes.addAttribute("error", "不存在该房间");
                    break;
                case 2:
                    redirectAttributes.addAttribute("error", "发生错误");
                    break;
            }
            return "redirect:/admin";
        }
        throw new RuntimeException("发生错误");
    }

    @PostMapping("/admin/commit-announcement")
    public String commitAnnouncement(@RequestParam("content") String content, RedirectAttributes redirectAttributes) {
        int commitAnnouncementStatus = Integer.parseInt(announcementMessageBoardFeign.commitAnnouncement(content));
        if (commitAnnouncementStatus == 0) {
            redirectAttributes.addAttribute("error", "成功提交公告");
        } else {
            redirectAttributes.addAttribute("error", "提交公告失败");
        }
        return "redirect:/admin";
    }

    @PostMapping("/admin/insert-unit")
    public String insertUnit(@RequestParam("unitNumber") String unitNumber, @RequestParam("caretaker") String caretaker,
                             @RequestParam("cleaner") String cleaner, RedirectAttributes redirectAttributes) {
        int insertUnitStatus = Integer.parseInt(unitRoomFeign.insertUnit(unitNumber, caretaker, cleaner));
        switch (insertUnitStatus) {
            case 0:
                redirectAttributes.addAttribute("error", "成功插入单元");
                break;
            case 1:
                redirectAttributes.addAttribute("error", "已经存在该单元");
                break;
            case 2:
                redirectAttributes.addAttribute("error", "发生错误");
                break;
        }
        return "redirect:/admin";
    }

    @PostMapping("/admin/insert-room")
    public String insertRoom(@RequestParam("unitNumber") String unitNumber, @RequestParam("roomNumber") String roomNumber,
                             RedirectAttributes redirectAttributes) {
        int insertRoomStatus = Integer.parseInt(unitRoomFeign.insertRoom(unitNumber, roomNumber, ""));
        switch (insertRoomStatus) {
            case 0:
                redirectAttributes.addAttribute("error", "成功插入房间");
                break;
            case 1:
                redirectAttributes.addAttribute("error", "不存在该单元");
                break;
            case 2:
                redirectAttributes.addAttribute("error", "已经存在该房间");
                break;
            case 3:
                redirectAttributes.addAttribute("error", "发生错误");
                break;
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/get-room")
    @ResponseBody
    public Room getRoom(@RequestParam("unitNumber") String unitNumber, @RequestParam("roomNumber") String roomNumber) {
        Room room = unitRoomFeign.getRoom(unitNumber, roomNumber);
        if (room.getHouseHolder() != null) {
            Residence houseHolder = residenceFeign.getResidence(room.getHouseHolder());
            room.setHouseHolder(houseHolder.getName());
        }
        return room;
    }

    @GetMapping("/admin/get-residences-by-room")
    @ResponseBody
    public ArrayList<Residence> getResidencesByRoom(@RequestParam("unitNumber") String unitNumber, @RequestParam("roomNumber") String roomNumber) {
        return residenceFeign.getResidencesByUnitNumberAndRoomNumber(unitNumber, roomNumber);
    }

    @GetMapping("/admin/get-unit")
    @ResponseBody
    public Unit getUnit(@RequestParam("unitNumber") String unitNumber) {
        return unitRoomFeign.getUnit(unitNumber);
    }

    @GetMapping("/admin/get-rooms-by-unit")
    @ResponseBody
    public ArrayList<Room> getRoomsByUnit(@RequestParam("unitNumber") String unitNumber) {
        ArrayList<Room> rooms = unitRoomFeign.selectRoomsByUnitNumber(unitNumber);
        for (Room room : rooms) {
            if (room.getHouseHolder() != null) {
                Residence houseHolder = residenceFeign.getResidence(room.getHouseHolder());
                room.setHouseHolder(houseHolder.getName());
            }
        }
        return rooms;
    }

    public String deleteMessageBoard(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteMessageBoardStatus = Integer.parseInt(announcementMessageBoardFeign.deleteMessageBoard(id));
        switch (deleteMessageBoardStatus) {
            case 0:
                redirectAttributes.addAttribute("error", "成功删除留言");
                break;
            case 1:
                redirectAttributes.addAttribute("error", "不存在该留言");
                break;
            case 2:
                redirectAttributes.addAttribute("error", "发生错误");
                break;
        }
        return "redirect:/admin";
    }

    @PostMapping("/admin/commit-feedback-to-message-board")
    public String commitFeedbackToMessageBoard(@RequestParam("id") Integer id, @RequestParam("feedback") String feedback,
                                               @RequestParam("type") String type, RedirectAttributes redirectAttributes) {
        if (type.equals("删除留言")) {
            return deleteMessageBoard(id, redirectAttributes);
        }
        int commitFeedbackToMessageBoardStatus = Integer.parseInt(announcementMessageBoardFeign.commitFeedbackToMessageBoard(id, feedback));
        switch (commitFeedbackToMessageBoardStatus) {
            case 0:
                redirectAttributes.addAttribute("error", "成功提交回复");
                break;
            case 1:
                redirectAttributes.addAttribute("error", "不存在该留言");
                break;
            case 2:
                redirectAttributes.addAttribute("error", "发生错误");
                break;
        }
        return "redirect:/admin";
    }
}
