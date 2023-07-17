package com.gzu.community_cloud.announcement_message_board;


import com.gzu.community_cloud.announcement_message_board.feign.ResidenceFeign;
import com.gzu.community_cloud.announcement_message_board.pojo.Announcement;
import com.gzu.community_cloud.announcement_message_board.pojo.MessageBoard;
import com.gzu.community_cloud.announcement_message_board.pojo.Residence;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class AnnouncementMessageBoardController {
    AnnouncementMessageBoardService announcementMessageBoardService;
    ResidenceFeign residenceFeign;

    public AnnouncementMessageBoardController(AnnouncementMessageBoardService announcementMessageBoardService,
                                              ResidenceFeign residenceFeign) {
        this.announcementMessageBoardService = announcementMessageBoardService;
        this.residenceFeign = residenceFeign;
    }

    @GetMapping("/feign/commit-message-board")
    @ResponseBody
    String commitMessageBoard(@RequestParam String username, @RequestParam String content) {
        return announcementMessageBoardService.commitMessageBoard(username, content).toString();
    }

    @GetMapping("/announcement-message-board/get-latest-announcement")
    @ResponseBody
    public Announcement getLatestAnnouncement() {
        return announcementMessageBoardService.getLatestAnnouncement();
    }

    @GetMapping("/announcement-message-board/get-message-board")
    @ResponseBody
    public ArrayList<MessageBoard> getMessageBoard() {
        ArrayList<MessageBoard> messageBoards = announcementMessageBoardService.getMessageBoard();
        for (MessageBoard messageBoard : messageBoards) {
            Residence residence = residenceFeign.getResidence(messageBoard.getUsername());
            messageBoard.setUsername(residence.getName());
        }
        return messageBoards;
    }
}
