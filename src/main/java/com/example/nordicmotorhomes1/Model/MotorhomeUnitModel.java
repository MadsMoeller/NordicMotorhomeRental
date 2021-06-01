package com.example.nordicmotorhomes1.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MotorhomeUnitModel {

    @Id
    private int motorhomeUnitID;
    private int motorhomeModelID;
    private int registrationYear;
    private int drivenKilometers;
    private String registration;
    private String notes;

    public MotorhomeUnitModel() {
    }

    public MotorhomeUnitModel(int motorhomeUnitID, int motorhomeModelID, int registrationYear, int drivenKilometers, String registration, String notes) {
        this.motorhomeUnitID = motorhomeUnitID;
        this.motorhomeModelID = motorhomeModelID;
        this.registrationYear = registrationYear;
        this.drivenKilometers = drivenKilometers;
        this.registration = registration;
        this.notes = notes;
    }

    public int getMotorhomeUnitID() {
        return motorhomeUnitID;
    }

    public void setMotorhomeUnitID(int motorhomeUnitID) {
        this.motorhomeUnitID = motorhomeUnitID;
    }

    public int getMotorhomeModelID() {
        return motorhomeModelID;
    }

    public void setMotorhomeModelID(int motorhomeModelID) {
        this.motorhomeModelID = motorhomeModelID;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public int getDrivenKilometers() {
        return drivenKilometers;
    }

    public void setDrivenKilometers(int drivenKilometers) {
        this.drivenKilometers = drivenKilometers;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "MotorhomeUnit{" +
                "motorhomeUnitID=" + motorhomeUnitID +
                ", motorhomeModelID=" + motorhomeModelID +
                ", registrationYear=" + registrationYear +
                ", drivenKilometers=" + drivenKilometers +
                ", registration='" + registration + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
