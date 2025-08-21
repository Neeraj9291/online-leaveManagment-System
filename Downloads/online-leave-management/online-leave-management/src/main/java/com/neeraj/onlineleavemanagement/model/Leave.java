package com.neeraj.onlineleavemanagement.model;

public class Leave {
    private String type;
    private String date;

    public Leave(String type, String date) {
        this.type = type;
        this.date = date;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
