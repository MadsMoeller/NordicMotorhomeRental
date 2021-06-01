package com.example.nordicmotorhomes1.Service;

import com.example.nordicmotorhomes1.Model.ExtraModel;
import com.example.nordicmotorhomes1.Repository.ExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraService {

    @Autowired
    ExtraRepository extraRepository;

    public List<ExtraModel> fetchAll(){
        return extraRepository.fetchAll();
    }

    public List<ExtraModel> fetchAllByBookingId(int bookingID){
        return extraRepository.fetchAllByBookingId(bookingID);
    }
}