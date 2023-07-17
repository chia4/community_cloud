package com.gzu.community_cloud.unit_room;


import com.gzu.community_cloud.unit_room.pojo.Room;
import com.gzu.community_cloud.unit_room.pojo.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UnitRoomService {
    UnitRoomMapper unitRoomMapper;

    @Autowired
    public UnitRoomService(UnitRoomMapper unitRoomMapper) {
        this.unitRoomMapper = unitRoomMapper;
    }

    public ArrayList<Unit> getUnits() {
        return unitRoomMapper.selectUnits();
    }

    public ArrayList<Room> getRooms() {
        return unitRoomMapper.selectRooms();
    }

    public Room getRoom(String unitNumber, String roomNumber) {
        return unitRoomMapper.selectRoom(unitNumber, roomNumber);
    }

    public Unit getUnit(String unitNumber) {
        return unitRoomMapper.selectUnit(unitNumber);
    }

    /**
     * 插入单元
     * 返回值:
     * 0 - 成功
     * 1 - 已经存在该楼栋
     * 2 - 其他错误
     */
    public Integer insertUnit(String unitNumber, String caretaker, String cleaner) {
        try {
            Unit unit = unitRoomMapper.selectUnit(unitNumber);
            if (unit == null) {
                unit = new Unit(unitNumber, caretaker, cleaner);
                unitRoomMapper.insertUnit(unit);
            } else {
                return 1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 2;
        }
        return 0;
    }

    /**
     * 根据单元插入房间
     * 返回值:
     * 0 - 成功
     * 1 - 不存在该楼栋
     * 2 - 已经存在该房间
     * 3 - 其他错误
     */
    public Integer insertRoom(String unitNumber, String roomNumber, String houseHolder) {
        try {
            if (unitNumber != null) {
                Room room = unitRoomMapper.selectRoom(unitNumber, roomNumber);
                if (room == null) {
                    room = new Room(unitNumber, roomNumber, houseHolder);
                    unitRoomMapper.insertRoom(room);
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

    public ArrayList<Room> selectRoomsByUnitNumber(String unitNumber) {
        return unitRoomMapper.selectRoomsByUnitNumber(unitNumber);
    }

    /**
     * 删除房间
     * 返回值:
     * 0 - 成功
     * 1 - 未删除
     * 2 - 其他错误
     */
    public Integer deleteRoom(String unitNumber, String roomNumber) {
        try {
            int deleteRoomStatus = unitRoomMapper.deleteRoom(unitNumber, roomNumber);
            if (deleteRoomStatus == 0) {
                return 1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 2;
        }
        return 0;
    }

    /**
     * 删除单元
     * 返回值:
     * 0 - 成功
     * 1 - 未删除
     * 2 - 其他错误
     */
    public Integer deleteUnit(String unitNumber) {
        try {
            int deleteUnitStatus = unitRoomMapper.deleteUnit(unitNumber);
            if (deleteUnitStatus == 0) {
                return 1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 2;
        }
        return 0;
    }

    /**
     * 设置户主
     * 返回值:
     * 0 - 成功
     * 1 - 未修改
     * 2 - 其他错误
     */
    public Integer setHouseHolder(String unitNumber, String roomNumber, String houseHolder) {
        try {
            int setHouseHolderStatus = unitRoomMapper.setHouseHolder(unitNumber, roomNumber, houseHolder);
            if (setHouseHolderStatus == 0) {
                return 1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 2;
        }
        return 0;
    }
}

