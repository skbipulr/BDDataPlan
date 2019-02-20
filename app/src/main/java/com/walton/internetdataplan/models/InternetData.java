package com.walton.internetdataplan.models;

/**
 * Created by Faruq on 1/5/2017.
 */

public class InternetData {
    String usegeData;
    String currentData;
    String previousData;
    String date;

    public InternetData(String usegeData, String currentData, String previousData) {
        this.usegeData = usegeData;
        this.currentData = currentData;
        this.previousData = previousData;
    }
    public InternetData(String usegeData, String currentData, String previousData,String date) {
        this.usegeData = usegeData;
        this.currentData = currentData;
        this.previousData = previousData;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPreviousData() {
        return previousData;
    }

    public void setPreviousData(String previousData) {
        this.previousData = previousData;
    }

    public String getUsegeData() {
        return usegeData;
    }

    public void setUsegeData(String usegeData) {
        this.usegeData = usegeData;
    }

    public String getCurrentData() {
        return currentData;
    }

    public void setCurrentData(String currentData) {
        this.currentData = currentData;
    }
}
