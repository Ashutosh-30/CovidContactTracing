package com.ash.pp.codesample.model;

public class Notification {

    private int notificationId;

    private int employeeId;

    private Notification notificationType;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Notification getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Notification notificationType) {
        this.notificationType = notificationType;
    }
}
