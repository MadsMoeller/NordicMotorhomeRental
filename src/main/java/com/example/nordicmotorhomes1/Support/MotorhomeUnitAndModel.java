package com.example.nordicmotorhomes1.Support;

public class MotorhomeUnitAndModel implements Comparable<MotorhomeUnitAndModel> {

    private int motorhomeUnitID;
    private int motorhomeModelID;
    private String brand;
    private String modelName;
    private int numberOfBeds;
    private int numberOfSeats;
    private int numberOfSeatBelts;
    private int weight;
    private int maxLoadedWeight;
    private String descriptionNote;
    private int registrationYear;
    private int drivenKilometers;
    private int pricePerDay;
    private String registration;
    private String notes;

    public MotorhomeUnitAndModel(int motorhomeUnitID, int motorhomeModelID, String brand, String modelName, int numberOfBeds,
                                 int numberOfSeats, int numberOfSeatBelts, int weight, int maxLoadedWeight, String descriptionNote,
                                 int registrationYear, int drivenKilometers, int pricePerDay, String registration, String notes) {
        this.motorhomeUnitID = motorhomeUnitID;
        this.motorhomeModelID = motorhomeModelID;
        this.brand = brand;
        this.modelName = modelName;
        this.numberOfBeds = numberOfBeds;
        this.numberOfSeats = numberOfSeats;
        this.numberOfSeatBelts = numberOfSeatBelts;
        this.weight = weight;
        this.maxLoadedWeight = maxLoadedWeight;
        this.descriptionNote = descriptionNote;
        this.registrationYear = registrationYear;
        this.drivenKilometers = drivenKilometers;
        this.pricePerDay = pricePerDay;
        this.registration = registration;
        this.notes = notes;
    }

    public int getMotorhomeUnitID() {
        return motorhomeUnitID;
    }

    public void setMotorhomeUnitID(int motorhomeUnitID) {
        this.motorhomeUnitID = motorhomeUnitID;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getMotorhomeModelID() {
        return motorhomeModelID;
    }

    public void setMotorhomeModelID(int motorhomeModelID) {
        this.motorhomeModelID = motorhomeModelID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeatBelts() {
        return numberOfSeatBelts;
    }

    public void setNumberOfSeatBelts(int numberOfSeatBelts) {
        this.numberOfSeatBelts = numberOfSeatBelts;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxLoadedWeight() {
        return maxLoadedWeight;
    }

    public void setMaxLoadedWeight(int maxLoadedWeight) {
        this.maxLoadedWeight = maxLoadedWeight;
    }

    public String getDescriptionNote() {
        return descriptionNote;
    }

    public void setDescriptionNote(String descriptionNote) {
        this.descriptionNote = descriptionNote;
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
    public int compareTo(MotorhomeUnitAndModel m){
        int brand = this.brand.compareTo(m.brand);
        if(brand == 0){
            int modelName = this.modelName.compareTo(m.modelName);
            return modelName;
        }
        return brand;
    }
}
