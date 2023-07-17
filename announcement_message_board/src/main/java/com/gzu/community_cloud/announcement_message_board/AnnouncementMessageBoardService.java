package com.gzu.community_cloud.announcement_message_board;


import com.gzu.community_cloud.announcement_message_board.pojo.Announcement;
import com.gzu.community_cloud.announcement_message_board.pojo.MessageBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class AnnouncementMessageBoardService {
    AnnouncementMessageBoardMapper announcementMessageBoardMapper;

    @Autowired
    public AnnouncementMessageBoardService(AnnouncementMessageBoardMapper announcementMessageBoardMapper) {
        this.announcementMessageBoardMapper = announcementMessageBoardMapper;
    }

    /**
     * 提交公告
     * 返回值:
     * 0 - 成功
     * 1 - 失败
     */
    public Integer commitAnnouncement(String content) {
        try {
            int timestamp = (int) (new Date().getTime() / 1000);
            announcementMessageBoardMapper.insertAnnouncement(new Announcement(null, timestamp, content));
        } catch (Exception exception) {
            exception.printStackTrace();
            return 1;
        }
        return 0;
    }

    public Announcement getLatestAnnouncement() {
        return announcementMessageBoardMapper.selectLatestAnnouncement();
    }

    /**
     * 提交留言
     * 返回值:
     * 0 - 成功
     * 1 - 失败
     */
    public Integer commitMessageBoard(String username, String content) {
        try {
            int timestamp = (int) (new Date().getTime() / 1000);
            announcementMessageBoardMapper.insertMessageBoard(new MessageBoard(null, username, timestamp, content, null));
        } catch (Exception exception) {
            exception.printStackTrace();
            return 1;
        }
        return 0;
    }

    public ArrayList<MessageBoard> getMessageBoard() {
        return announcementMessageBoardMapper.selectMessageBoard();
    }

    /**
     * 删除留言
     * 返回值:
     * 0 - 成功
     * 1 - 不存在该留言
     * 2 - 失败
     */
    public Integer deleteMessageBoard(Integer id) {
        try {
            int deleteMessageBoardStatus = announcementMessageBoardMapper.deleteMessageBoard(id);
            if (deleteMessageBoardStatus == 0) {
                return 1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 2;
        }
        return 0;
    }

    /**
     * 回复留言
     * 返回值:
     * 0 - 成功
     * 1 - 不存在该留言
     * 2 - 失败
     */
    public Integer commitFeedbackToMessageBoard(Integer id, String feedback) {
        try {
            int updateFeedbackToMessageBoardStatus = announcementMessageBoardMapper.updateFeedbackToMessageBoard(id, feedback);
            if (updateFeedbackToMessageBoardStatus == 0) {
                return 1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 2;
        }
        return 0;
    }
}
