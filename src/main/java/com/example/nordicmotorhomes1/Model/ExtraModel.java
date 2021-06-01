package com.example.nordicmotorhomes1.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExtraModel {

    @Id
    private int extraID;
    private String item;
    private int price;

    public ExtraModel() {
    }

    public ExtraModel(int extraID, String item, int price) {
        this.extraID = extraID;
        this.item = item;
        this.price = price;
    }

    public int getExtraID() {
        return extraID;
    }

    public void setExtraID(int extraID) {
        this.extraID = extraID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Extra{" +
                "extraID=" + extraID +
                ", item='" + item + '\'' +
                ", price=" + price +
                '}';
    }
}
