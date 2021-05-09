package com.example.finalevaluvation;

public class Bus {

    private String busName;
    private String busNo;
    private String bFrom;
    private String bTo;
    private String bTelephone;

    public Bus() {
    }

    public Bus(String busName, String busNo, String bFrom, String bTo, String bTelephone) {
        this.busName = busName;
        this.busNo = busNo;
        this.bFrom = bFrom;
        this.bTo = bTo;
        this.bTelephone = bTelephone;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getbFrom() {
        return bFrom;
    }

    public void setbFrom(String bFrom) {
        this.bFrom = bFrom;
    }

    public String getbTo() {
        return bTo;
    }

    public void setbTo(String bTo) {
        this.bTo = bTo;
    }

    public String getbTelephone() {
        return bTelephone;
    }

    public void setbTelephone(String bTelephone) {
        this.bTelephone = bTelephone;
    }
}
