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

    @GetMapping("/feign/insert-announcement-like")
    @ResponseBody
    String insertAnnouncementLike(@RequestParam Integer id, @RequestParam String username) {
        return announcementMessageBoardService.insertAnnouncementLike(id, username).toString();
    }

    @GetMapping("/feign/insert-message-board-like")
    @ResponseBody
    String insertMessageBoardLike(@RequestParam Integer id, @RequestParam String username) {
        return announcementMessageBoardService.insertMessageBoardLike(id, username).toString();
    }

    @GetMapping("/feign/commit-announcement")
    @ResponseBody
    String commitAnnouncement(@RequestParam String content) {
        return announcementMessageBoardService.commitAnnouncement(content).toString();
    }

    @GetMapping("/feign/delete-message-board")
    @ResponseBody
    String deleteMessageBoard(@RequestParam Integer id) {
        return announcementMessageBoardService.deleteMessageBoard(id).toString();
    }

    @GetMapping("/feign/commit-feedback-to-message-board")
    @ResponseBody
    String commitFeedbackToMessageBoard(Integer id, String feedback) {
        return announcementMessageBoardService.commitFeedbackToMessageBoard(id, feedback).toString();
    }

    @GetMapping("/feign/commit-message-board")
    @ResponseBody
    String commitMessageBoard(@RequestParam String username, @RequestParam String content) {
        return announcementMessageBoardService.commitMessageBoard(username, content).toString();
    }

    @GetMapping("/announcement-message-board/get-latest-announcement")
    @ResponseBody
    public Announcement getLatestAnnouncement() {
        Announcement announcement = announcementMessageBoardService.getLatestAnnouncement();
        int likes = announcementMessageBoardService.countLikesForAnnouncement(announcement.getId());
        announcement.setLikes(likes);
        return announcement;
    }

    @GetMapping("/announcement-message-board/get-message-board")
    @ResponseBody
    public ArrayList<MessageBoard> getMessageBoard() {
        ArrayList<MessageBoard> messageBoards = announcementMessageBoardService.getMessageBoard();
        for (MessageBoard messageBoard : messageBoards) {
            Residence residence = residenceFeign.getResidence(messageBoard.getUsername());
            messageBoard.setUsername(residence.getName());
            int likes = announcementMessageBoardService.countLikesForMessageBoard(messageBoard.getId());
            messageBoard.setLikes(likes);
        }
        return messageBoards;
    }
}
