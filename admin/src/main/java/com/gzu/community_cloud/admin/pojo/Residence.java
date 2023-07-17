package com.gzu.community_cloud.admin.pojo;

public class Residence {
    private String username;
    private String password;
    private String name;
    private String unitNumber;
    private String roomNumber;

    public Residence() {
    }

    public Residence(String username, String password, String name, String unitNumber, String roomNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.unitNumber = unitNumber;
        this.roomNumber = roomNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "residence{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", unitNumber='" + unitNumber + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }
}
