package com.walton.internetdataplan.utitls;

/**
 * Created by Faruq on 12/26/2016.
 */

public class DataPlan {
    int id;
    String startDate,endDate,duration,dataSize,dataSizeType;

    public DataPlan(String startDate, String endDate, String duration, String dataSize, String dataSizeType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.dataSize = dataSize;
        this.dataSizeType = dataSizeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getDataSizeType() {
        return dataSizeType;
    }

    public void setDataSizeType(String dataSizeType) {
        this.dataSizeType = dataSizeType;
    }
}
