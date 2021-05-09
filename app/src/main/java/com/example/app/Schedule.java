package com.example.app;

public class Schedule {

     String regNo;
     String date;
     String route;
     String departure;
     String arrival;


    public Schedule() {
    }

    public Schedule(String regNo, String date, String route, String departure, String arrival) {

        this.regNo = regNo;
        this.date = date;
        this.route = route;
        this.departure = departure;
        this.arrival = arrival;
    }


    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.regNo = date;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
}
