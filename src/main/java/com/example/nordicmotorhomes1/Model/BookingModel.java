package com.example.nordicmotorhomes1.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class BookingModel {

    @Id
    private int bookingID;
    private int motorhomeUnitID;
    private int customerID;
    private String startDate;
    private String endDate;
    private String pickupTime;
    private String pickupPlace;
    private String dropoffTime;
    private String dropoffPlace;

    public BookingModel() {
    }

    public BookingModel(int bookingID, int motorhomeUnitID, int customerID, String startDate, String endDate, String pickupTime, String pickupPlace, String dropoffTime, String dropoffPlace) {
        this.bookingID = bookingID;
        this.motorhomeUnitID = motorhomeUnitID;
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupTime = pickupTime;
        this.pickupPlace = pickupPlace;
        this.dropoffTime = dropoffTime;
        this.dropoffPlace = dropoffPlace;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getMotorhomeUnitID() {
        return motorhomeUnitID;
    }

    public void setMotorhomeUnitID(int motorhomeUnitID) {
        this.motorhomeUnitID = motorhomeUnitID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getPickupPlace() {
        return pickupPlace;
    }

    public void setPickupPlace(String pickupPlace) {
        this.pickupPlace = pickupPlace;
    }

    public String getDropoffTime() {
        return dropoffTime;
    }

    public void setDropoffTime(String dropoffTime) {
        this.dropoffTime = dropoffTime;
    }

    public String getDropoffPlace() {
        return dropoffPlace;
    }

    public void setDropoffPlace(String dropoffPlace) {
        this.dropoffPlace = dropoffPlace;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", motorhomeUnitID=" + motorhomeUnitID +
                ", customerID=" + customerID +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", pickupTime='" + pickupTime + '\'' +
                ", pickupPlace='" + pickupPlace + '\'' +
                ", dropoffTime='" + dropoffTime + '\'' +
                ", dropoffPlace='" + dropoffPlace + '\'' +
                '}';
    }

    //This method calculates the duration of the booking in days.
    public int getDurationInDays(){
        LocalDate dateStart = LocalDate.parse(startDate);
        LocalDate dateEnd = LocalDate.parse(endDate);
        return (int) ChronoUnit.DAYS.between(dateStart, dateEnd);
    }
}
