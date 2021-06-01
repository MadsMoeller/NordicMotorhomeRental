package com.example.nordicmotorhomes1.Service;

import com.example.nordicmotorhomes1.Model.MotorhomeModelModel;
import com.example.nordicmotorhomes1.Repository.MotorhomeModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorhomeModelService {

    @Autowired
    MotorhomeModelRepository motorhomeModelRepository;

    public List<MotorhomeModelModel> fetchAll(){
        return motorhomeModelRepository.fetchAll();
    }

    public MotorhomeModelModel addMotorhomeModel(MotorhomeModelModel mm){
        return motorhomeModelRepository.addMotorhomeModel(mm);
    }

    public MotorhomeModelModel findMotorhomeModelById(int motorhomeModelID){
        return motorhomeModelRepository.findMotorhomeModelById(motorhomeModelID);
    }

    public MotorhomeModelModel updateMotorhomeModel(int motorhomeModelID, MotorhomeModelModel mm){
        return motorhomeModelRepository.updateMotorhomeModel(motorhomeModelID, mm);
    }

    public List<MotorhomeModelModel> searchMotorhomeModel(String sd, String ed, String ppd, String br, String mod, String nob, String nos, String nosb){
        return motorhomeModelRepository.searchMotorhomeModel(sd, ed, ppd, br, mod, nob, nos, nosb);
    }
    public List<MotorhomeModelModel> searchMotorhomeModelParam(String br, String mod, String ppd, String nob, String nos, String nosb, String w, String mlw, String mdn){
        return motorhomeModelRepository.searchMotorhomeModelParam(br, mod, ppd, nob, nos, nosb, w, mlw, mdn);
    }
}
