package com.example.nordicmotorhomes1.Service;

import com.example.nordicmotorhomes1.Model.CityModel;
import com.example.nordicmotorhomes1.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public CityModel findCityByZipcode(String zipcode){
        return cityRepository.findCityByZipcode(zipcode);
    }
}
