package com.example.nordicmotorhomes1.Service;

import com.example.nordicmotorhomes1.Model.BookingModel;
import com.example.nordicmotorhomes1.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public List<BookingModel> fetchAll(){
        return bookingRepository.fetchAll();
    }

    public BookingModel fetchLatest(){ return bookingRepository.fetchLatest(); }

    public BookingModel addBooking(BookingModel b){
        return bookingRepository.addBooking(b);
    }

    public BookingModel findBookingById(int bookingID){
        return bookingRepository.findBookingById(bookingID);
    }

    public BookingModel updateBooking(int bookingID, BookingModel b){
        return bookingRepository.updateBooking(bookingID, b);
    }

    public List<BookingModel> searchBookings(String fromDate, String toDate, String bookingID, String customerID, String motorhomeUnitID){
        return bookingRepository.searchBookings(fromDate, toDate, bookingID, customerID, motorhomeUnitID);
    }
}
