package com.example.nordicmotorhomes1.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MotorhomeModelModel {

    @Id
    private int motorhomeModelID;
    private String brand;
    private String modelName;
    private int pricePerDay;
    private int numberOfBeds;
    private int numberOfSeats;
    private int numberOfSeatBelts;
    private int weight;
    private int maxLoadedWeight;
    private String descriptionNote;

    public MotorhomeModelModel() {
    }

    public MotorhomeModelModel(int motorhomeModelID, String brand, String modelName, int pricePerDay, int numberOfBeds, int numberOfSeats, int numberOfSeatBelts, int weight, int maxLoadedWeight, String descriptionNote) {
        this.motorhomeModelID = motorhomeModelID;
        this.brand = brand;
        this.modelName = modelName;
        this.pricePerDay = pricePerDay;
        this.numberOfBeds = numberOfBeds;
        this.numberOfSeats = numberOfSeats;
        this.numberOfSeatBelts = numberOfSeatBelts;
        this.weight = weight;
        this.maxLoadedWeight = maxLoadedWeight;
        this.descriptionNote = descriptionNote;
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

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
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

    @Override
    public String toString() {
        return "MotorhomeModel{" +
                "motorhomeModelID=" + motorhomeModelID +
                ", brand='" + brand + '\'' +
                ", modelName='" + modelName + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", numberOfBeds=" + numberOfBeds +
                ", numberOfSeats=" + numberOfSeats +
                ", numberOfSeatBelts=" + numberOfSeatBelts +
                ", weight=" + weight +
                ", maxLoadedWeight=" + maxLoadedWeight +
                ", description='" + descriptionNote + '\'' +
                '}';
    }
}
