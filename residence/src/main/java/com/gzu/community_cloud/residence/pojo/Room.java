package com.gzu.community_cloud.residence.pojo;

public class Room {
    private String unitNumber;
    private String roomNumber;
    private String houseHolder;

    public Room() {
    }

    public Room(String unitNumber, String roomNumber, String houseHolder) {
        this.unitNumber = unitNumber;
        this.roomNumber = roomNumber;
        this.houseHolder = houseHolder;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getHouseHolder() {
        return houseHolder;
    }

    public void setHouseHolder(String houseHolder) {
        this.houseHolder = houseHolder;
    }

    @Override
    public String toString() {
        return "Room{" +
                "unitNumber='" + unitNumber + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", houseHolder='" + houseHolder + '\'' +
                '}';
    }
}
