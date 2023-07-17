package com.gzu.community_cloud.residence;


import com.gzu.community_cloud.residence.feign.UnitRoomFeign;
import com.gzu.community_cloud.residence.pojo.Residence;
import com.gzu.community_cloud.residence.pojo.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ResidenceService {
    ResidenceMapper residenceMapper;
    UnitRoomFeign unitRoomFeign;

    public ResidenceService(ResidenceMapper residenceMapper, UnitRoomFeign unitRoomFeign) {
        this.residenceMapper = residenceMapper;
        this.unitRoomFeign = unitRoomFeign;
    }

    public Residence getResidence(String username) {
        Residence residence = residenceMapper.selectResidenceByUsername(username);
        if (residence != null) {
            residence.setPassword(null);
        }
        return residence;
    }

    /**
     * 注册用户
     * 返回值:
     * 0 - 成功
     * 1 - 用户名重复
     * 2 - 不存在该房间
     * 3 - 其他错误
     */
    public Integer register(String username, String password, String name, String unitNumber, String roomNumber) {
        try {
            Residence existingResidence = residenceMapper.selectResidenceByUsername(username);
            if (existingResidence == null) {
                Room room = unitRoomFeign.getRoom(unitNumber, roomNumber);
                if (room != null) {
                    Residence residence = new Residence(username, password, name, unitNumber, roomNumber);
                    residenceMapper.insertResidence(residence);
                } else {
                    return 2;
                }
            } else {
                return 1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 3;
        }
        return 0;
    }

    /**
     * 登录用户
     * 返回值:
     * 0 - 验证通过
     * 1 - 用户不存在
     * 2 - 验证失败
     * 3 - 其他错误
     */
    public Integer login(String username, String password) {
        try {
            Residence residence = residenceMapper.selectResidenceByUsername(username);
            if (residence != null) {
                if (!password.equals(residence.getPassword())) {
                    return 2;
                }
            } else {
                return 1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 3;
        }
        return 0;
    }

    public ArrayList<Residence> getResidencesByUnitNumberAndRoomNumber(String unitNumber, String roomNumber) {
        return residenceMapper.selectResidencesByUnitNumberAndRoomNumber(unitNumber, roomNumber);
    }
}
