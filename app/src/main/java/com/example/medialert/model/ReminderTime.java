package com.example.medialert.model;

public class ReminderTime {

    private int Hour;
    private int Minute;

    public ReminderTime() {
    }

    public ReminderTime(int hour, int minute) {
        Hour = hour;
        Minute = minute;
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }

    public int getMinute() {
        return Minute;
    }

    public void setMinute(int minute) {
        Minute = minute;
    }
}
