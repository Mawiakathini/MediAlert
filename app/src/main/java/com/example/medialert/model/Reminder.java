package com.example.medialert.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reminder {

    private String id;
    private String uid;

    private ReminderTime timestamp;

    private String medicineName;

    private Dose dose;

    private String whenConsumed;

    private String frequency;

    private Duration duration;

    public Reminder() {
    }

    public Reminder(String id, String uid, ReminderTime timestamp, String medicineName, Dose dose, String whenConsumed, String frequency, Duration duration) {
        this.id = id;
        this.uid = uid;
        this.timestamp = timestamp;
        this.medicineName = medicineName;
        this.dose = dose;
        this.whenConsumed = whenConsumed;
        this.frequency = frequency;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ReminderTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ReminderTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getWhenConsumed() {
        return whenConsumed;
    }

    public void setWhenConsumed(String whenConsumed) {
        this.whenConsumed = whenConsumed;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Dose getDose() {
        return dose;
    }

    public void setDose(Dose dose) {
        this.dose = dose;
    }


}
