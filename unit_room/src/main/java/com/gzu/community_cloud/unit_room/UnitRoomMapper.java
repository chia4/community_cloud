package com.gzu.community_cloud.unit_room;


import com.gzu.community_cloud.unit_room.pojo.Room;
import com.gzu.community_cloud.unit_room.pojo.Unit;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface UnitRoomMapper {
    @Select("select * from unit")
    ArrayList<Unit> selectUnits();

    @Select("select * from room")
    ArrayList<Room> selectRooms();

    @Select("select * from unit where unit_number = #{unitNumber}")
    Unit selectUnit(String unitNumber);

    @Select("select * from room where unit_number = #{unitNumber} and room_number = #{roomNumber}")
    Room selectRoom(String unitNumber, String roomNumber);

    @Select("select * from room where unit_number = #{unitNumber}")
    ArrayList<Room> selectRoomsByUnitNumber(String unitNumber);

    @Insert("insert into unit (unit_number, caretaker, cleaner) values (#{unitNumber}, #{caretaker}, #{cleaner})")
    Integer insertUnit(Unit unit);

    @Insert("insert into room (unit_number, room_number, house_holder) values (#{unitNumber}, #{roomNumber}, #{houseHolder})")
    Integer insertRoom(Room room);

    @Delete("delete from room where unit_number = #{unitNumber} and room_number = #{roomNumber}")
    Integer deleteRoom(String unitNumber, String roomNumber);

    @Delete("delete from unit where unit_number = #{unitNumber}")
    Integer deleteUnit(String unitNumber);

    @Update("update room set house_holder = #{houseHolder} where unit_number = #{unitNumber} and room_number = #{roomNumber}")
    Integer setHouseHolder(String unitNumber, String roomNumber, String houseHolder);
}
