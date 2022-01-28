package com.example.fit4u;

public class MyListData {
    private String description;
    private String time;
    private String calories;


    public MyListData(String description, String time, String calories) {
        this.description = description;
        this.time=time;
        this.calories=calories;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getCalories() {
        return calories;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}