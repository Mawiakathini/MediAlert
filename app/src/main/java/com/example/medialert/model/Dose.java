package com.example.medialert.model;

public class Dose {
    private Long dose;
    private String form;

    public Dose() {
    }

    public Dose(Long dose, String form) {
        this.dose = dose;
        this.form = form;
    }

    public Long getDose() {
        return dose;
    }

    public void setDose(Long dose) {
        this.dose = dose;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}
