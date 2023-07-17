package com.gzu.community_cloud.announcement_message_board;


import com.gzu.community_cloud.announcement_message_board.pojo.Announcement;
import com.gzu.community_cloud.announcement_message_board.pojo.MessageBoard;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface AnnouncementMessageBoardMapper {
    @Select("select * from announcement where id = (select max(id) from announcement)")
    Announcement selectLatestAnnouncement();

    @Insert("insert into announcement (id, timestamp, content) values (#{id}, #{timestamp}, #{content})")
    Integer insertAnnouncement(Announcement announcement);

    @Insert("insert into message_board (username, timestamp, content) values (#{username}, #{timestamp}, #{content})")
    Integer insertMessageBoard(MessageBoard messageBoard);

    @Select("select * from message_board order by id desc")
    ArrayList<MessageBoard> selectMessageBoard();

    @Delete("delete from message_board where id = #{id}")
    Integer deleteMessageBoard(Integer id);

    @Update("update message_board set feedback = #{feedback} where id = #{id}")
    Integer updateFeedbackToMessageBoard(Integer id, String feedback);

    @Insert("insert into announcement_like (id, username) values (#{id}, #{username})")
    Integer insertAnnouncementLike(Integer id, String username);

    @Select("select count(*) from announcement_like where id = #{id}")
    Integer countLikesForAnnouncement(Integer id);

    @Insert("insert into message_board_like (id, username) values (#{id}, #{username})")
    Integer insertMessageBoardLike(Integer id, String username);

    @Select("select count(*) from message_board_like where id = #{id}")
    Integer countLikesForMessageBoard(Integer id);
}
