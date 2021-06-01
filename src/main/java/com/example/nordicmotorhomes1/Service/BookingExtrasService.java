package com.example.nordicmotorhomes1.Service;

import com.example.nordicmotorhomes1.Model.BookingExtrasModel;
import com.example.nordicmotorhomes1.Repository.BookingExtrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingExtrasService{
    @Autowired
    BookingExtrasRepository bookingExtrasRepository;

    public List<BookingExtrasModel> fetchAll(){
        return bookingExtrasRepository.fetchAll();
    }

    public BookingExtrasModel addBookingExtras(BookingExtrasModel be){
        return bookingExtrasRepository.addBookingExtras(be);
    }

    public BookingExtrasModel findBookingExtrasByIds(int bookingID, int extraID){
        return bookingExtrasRepository.findBookingExtrasByIds(bookingID, extraID);
    }

    public Boolean deleteBookingExtras(int bookingID, int extraID){
        return bookingExtrasRepository.deleteBookingExtras(bookingID, extraID);
    }

    public void deleteBookingExtrasWithZeroAmount(){
        bookingExtrasRepository.deleteBookingExtrasWithZeroAmount();
    }

    public List<BookingExtrasModel> fetchAllByBookingId(int bookingID){
        return bookingExtrasRepository.fetchAllByBookingId(bookingID);
    }
}
