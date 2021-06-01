package com.example.nordicmotorhomes1.Service;

import com.example.nordicmotorhomes1.Model.BookingModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCalculatorTest {

    @Test
    void getPriceOfContract() {
        PriceCalculator pc = new PriceCalculator();
        BookingModel b = new BookingModel();
        b.setStartDate("2021-06-25");
        b.setEndDate("2021-07-14");
        assertEquals(39425.0, pc.getPriceOfContract(1200, b, 155));
    }

    @Test
    void getDailyPriceOfExtraItems() {
        PriceCalculator pc = new PriceCalculator();
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        ArrayList<String> item1 = new ArrayList<>();
        ArrayList<String> item2 = new ArrayList<>();
        ArrayList<String> item3 = new ArrayList<>();
        item1.add("itemName");  //name of item1
        item1.add("2");         //amount of item1
        item1.add("50");        //price of item1
        item2.add("itemName");  //name of item2
        item2.add("1");         //amount of item2
        item2.add("25");        //price of item2
        item3.add("itemName");  //name of item3
        item3.add("3");         //amount of item3
        item3.add("10");        //price of item3
        items.add(item1);
        items.add(item2);
        items.add(item3);
        assertEquals(155, pc.getDailyPriceOfExtraItems(items));
    }

    @Test
    void seasonMultiplier() {
        PriceCalculator pc = new PriceCalculator();
        BookingModel b = new BookingModel();
        b.setStartDate("2021-06-25");
        b.setEndDate("2021-07-14");
        assertEquals(1.6, pc.seasonMultiplier(b));
    }
}