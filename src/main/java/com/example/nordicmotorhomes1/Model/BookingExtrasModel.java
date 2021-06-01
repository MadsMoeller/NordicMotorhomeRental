package com.example.nordicmotorhomes1.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookingExtrasModel implements java.io.Serializable{

    @Id
    private int bookingID;
    @Id
    private int extraID;
    private int amount;

    public BookingExtrasModel() {
    }

    public BookingExtrasModel(int bookingID, int extraID, int amount) {
        this.bookingID = bookingID;
        this.extraID = extraID;
        this.amount = amount;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getExtraID() {
        return extraID;
    }

    public void setExtraID(int extraID) {
        this.extraID = extraID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BookingExtras{" +
                "bookingID=" + bookingID +
                ", extraID=" + extraID +
                ", amount=" + amount +
                '}';
    }
}
