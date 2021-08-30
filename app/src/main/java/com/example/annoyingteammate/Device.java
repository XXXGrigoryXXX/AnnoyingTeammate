package com.example.annoyingteammate;

public class Device {

    private int dbId;
    private String name;
    private String description;
    private String notification;

    public Device(int dbId, String name, String description, String notification) {

        this.setDbId(dbId);
        this.setName(name);
        this.setDescription(description);
        this.setNotification(notification);
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
