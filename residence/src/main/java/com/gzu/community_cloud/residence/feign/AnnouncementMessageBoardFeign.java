package com.gzu.community_cloud.residence.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("announcement-message-board")
public interface AnnouncementMessageBoardFeign {
    @GetMapping("/feign/commit-message-board")
    String commitMessageBoard(@RequestParam String username, @RequestParam String content);

    @GetMapping("/feign/insert-announcement-like")
    String insertAnnouncementLike(@RequestParam Integer id, @RequestParam String username);

    @GetMapping("/feign/insert-message-board-like")
    String insertMessageBoardLike(@RequestParam Integer id, @RequestParam String username);
}
