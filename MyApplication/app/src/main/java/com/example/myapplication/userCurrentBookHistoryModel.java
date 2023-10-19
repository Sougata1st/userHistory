package com.example.myapplication;

public class userCurrentBookHistoryModel {
    private Long TimeStamp;
    private String FromWhere,ToWhere,DriverUid;
    Boolean ratingGiven;

    public userCurrentBookHistoryModel() {
    }

    public userCurrentBookHistoryModel(Long timeStamp, String fromWhere, String toWhere, String DriverUid, Boolean ratingGiven) {
        TimeStamp = timeStamp;
        FromWhere = fromWhere;
        ToWhere = toWhere;
        this.DriverUid = DriverUid;
        this.ratingGiven=ratingGiven;
    }

    public Boolean getRatingGiven() {
        return ratingGiven;
    }

    public void setRatingGiven(Boolean ratingGiven) {
        this.ratingGiven = ratingGiven;
    }

    public Long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getFromWhere() {
        return FromWhere;
    }

    public void setFromWhere(String fromWhere) {
        FromWhere = fromWhere;
    }

    public String getToWhere() {
        return ToWhere;
    }

    public void setToWhere(String toWhere) {
        ToWhere = toWhere;
    }

    public String getDriverUid() {
        return DriverUid;
    }

    public void setDriverUid(String driverUid) {
        DriverUid = driverUid;
    }
}
