package com.ash.pp.codesample.model;

public class Meeting {

    private int meetingId;

    private int meetingRoomNumber;

    private int meetingFloorNumber;

    private String startTime;

    private String endTime;

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public int getMeetingRoomNumber() {
        return meetingRoomNumber;
    }

    public void setMeetingRoomNumber(int meetingRoomNumber) {
        this.meetingRoomNumber = meetingRoomNumber;
    }

    public int getMeetingFloorNumber() {
        return meetingFloorNumber;
    }

    public void setMeetingFloorNumber(int meetingFloorNumber) {
        this.meetingFloorNumber = meetingFloorNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
