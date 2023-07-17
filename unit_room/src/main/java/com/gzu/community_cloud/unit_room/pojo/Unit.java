package com.gzu.community_cloud.unit_room.pojo;

public class Unit {
    private String unitNumber;
    private String caretaker;
    private String cleaner;

    public Unit() {
    }

    public Unit(String unitNumber, String caretaker, String cleaner) {
        this.unitNumber = unitNumber;
        this.caretaker = caretaker;
        this.cleaner = cleaner;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(String caretaker) {
        this.caretaker = caretaker;
    }

    public String getCleaner() {
        return cleaner;
    }

    public void setCleaner(String cleaner) {
        this.cleaner = cleaner;
    }

    @Override
    public String toString() {
        return "Building{" +
                "unitNumber='" + unitNumber + '\'' +
                ", caretaker='" + caretaker + '\'' +
                ", cleaner='" + cleaner + '\'' +
                '}';
    }
}
