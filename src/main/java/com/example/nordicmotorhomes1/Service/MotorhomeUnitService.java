package com.example.nordicmotorhomes1.Service;

import com.example.nordicmotorhomes1.Model.MotorhomeModelModel;
import com.example.nordicmotorhomes1.Model.MotorhomeUnitModel;
import com.example.nordicmotorhomes1.Repository.MotorhomeUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorhomeUnitService {
    @Autowired
    MotorhomeUnitRepository motorhomeUnitRepository;

    public List<MotorhomeUnitModel> fetchAll(){
        return motorhomeUnitRepository.fetchAll();
    }

    public List<MotorhomeUnitModel> fetchAllByMotorhomeModelID(int motorhomeModelID){
        return motorhomeUnitRepository.fetchAllByMotorhomeModelID(motorhomeModelID);
    }

    public MotorhomeUnitModel addMotorhomeUnit(MotorhomeUnitModel mhUnit){
        return motorhomeUnitRepository.addMotorhomeUnit(mhUnit);
    }

    public MotorhomeUnitModel findMotorhomeUnitbyID(int id){
        return motorhomeUnitRepository.findMotorhomeUnitbyID(id);
    }

    public MotorhomeUnitModel updateMotorhomeUnit(MotorhomeUnitModel mhUnit){
        return motorhomeUnitRepository.updateMotorhomeUnit(mhUnit);
    }
    public List<MotorhomeUnitModel> searchMotorhome(String sd, String ed, String ppd, String nob, String nos, String nosb){
        return motorhomeUnitRepository.searchMotorhome(sd, ed, ppd, nob, nos, nosb);
    }

    public List<MotorhomeUnitModel> searchMotorhomeUnit(String ry, String ryf, String ryt, String dk, String dkf, String dkt, String r, String un){
        return motorhomeUnitRepository.searchMotorhomeUnit(ry, ryf, ryt, dk, dkf, dkt, r, un);
    }
    public List<MotorhomeModelModel> searchMotorhomeModel(String b, String m, String nob, String nos, String nosb, String w, String mlw, String ppd, String mdn){
        return motorhomeUnitRepository.searchMotorhomeModel(b, m, nob, nos, nosb, w, mlw, ppd, mdn);
    }

    public List<MotorhomeUnitModel> searchAvailableMotorhomeUnit(String startDate, String endDate, int motorhomeModelID){
        return motorhomeUnitRepository.searchAvailableMotorhomeUnit(startDate, endDate, motorhomeModelID);
    }
}
