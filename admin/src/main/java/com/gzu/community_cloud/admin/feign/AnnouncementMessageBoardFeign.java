package com.gzu.community_cloud.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("announcement-message-board")
public interface AnnouncementMessageBoardFeign {
    @GetMapping("/feign/commit-announcement")
    String commitAnnouncement(@RequestParam String content);

    @GetMapping("/feign/delete-message-board")
    String deleteMessageBoard(@RequestParam Integer id);

    @GetMapping("/feign/commit-feedback-to-message-board")
    String commitFeedbackToMessageBoard(@RequestParam Integer id, @RequestParam String feedback);
}
