package com.gzu.community_cloud.residence;


import com.gzu.community_cloud.residence.pojo.Residence;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface ResidenceMapper {
    @Insert("insert into residence (username, password, name, unit_number, room_number) values (#{username}, #{password}, #{name}, #{unitNumber}, #{roomNumber})")
    Integer insertResidence(Residence residence);

    @Select("select * from residence where username = #{username}")
    Residence selectResidenceByUsername(String username);

    @Select("select username, name, unit_number, room_number from residence where unit_number = #{unitNumber} and room_number = #{roomNumber}")
    ArrayList<Residence> selectResidencesByUnitNumberAndRoomNumber(String unitNumber, String roomNumber);
}
