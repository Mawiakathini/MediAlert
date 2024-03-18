package com.example.medialert.model;

public class Duration {
    private int value;
    private String  form;

    public Duration() {
    }

    public Duration(int value, String form) {
        this.value = value;
        this.form = form;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}
