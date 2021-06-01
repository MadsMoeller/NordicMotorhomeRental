package com.example.nordicmotorhomes1.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingModelTest {

    @Test
    void getDurationInDays() {
        BookingModel b = new BookingModel();
        b.setStartDate("2021-06-25");
        b.setEndDate("2021-07-14");
        assertEquals(19, b.getDurationInDays());
    }
}