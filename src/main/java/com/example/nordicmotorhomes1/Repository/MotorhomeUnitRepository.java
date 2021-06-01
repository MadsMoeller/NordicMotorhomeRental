package com.example.nordicmotorhomes1.Repository;

import com.example.nordicmotorhomes1.Model.MotorhomeModelModel;
import com.example.nordicmotorhomes1.Model.MotorhomeUnitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorhomeUnitRepository {
    @Autowired
    JdbcTemplate template;

    /* The method uses a JdbcTemplate object to query the database to return a list of all motorhome units
     * stored there. */
    public List<MotorhomeUnitModel> fetchAll(){
        String sql = "SELECT * FROM MotorhomeUnit";
        RowMapper<MotorhomeUnitModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeUnitModel.class);
        return template.query(sql, rowMapper);
    }

    /* The method uses a JdbcTemplate object to query the database to return a list of all motorhome models
     * stored there with a specific motorhomeModelID. */
    public List<MotorhomeUnitModel> fetchAllByMotorhomeModelID(int motorhomeModelID){
        String sql = "SELECT * FROM motorhomeUnit " +
                "WHERE motorhomeModelID = ?";
        RowMapper<MotorhomeUnitModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeUnitModel.class);
        return template.query(sql, rowMapper, motorhomeModelID);
    }

    /* The method uses a JdbcTemplate object to insert a MoterhomeUnit entity into the database with
     * the data corresponding to the MoterhomeUnitModel object taken as parameter. */
    public MotorhomeUnitModel addMotorhomeUnit(MotorhomeUnitModel mhUnit){
        String sql = "INSERT INTO MotorhomeUnit (MotorhomeUnitID, motorhomeModelID, registrationYear, drivenKilometers, registration, notes) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, mhUnit.getMotorhomeUnitID(), mhUnit.getMotorhomeModelID(), mhUnit.getRegistrationYear(), mhUnit.getDrivenKilometers(), mhUnit.getRegistration(), mhUnit.getNotes());
        return null;
    }

    /* The method uses a JdbcTemplate object to query the database returning a MotorhomeUnitModel
     * object mathcing the entity with the corresponding motorhomeUnitID. */
    public MotorhomeUnitModel findMotorhomeUnitbyID(int id){
        String sql = "SELECT * FROM MotorhomeUnit WHERE MotorhomeUnitID = ?";
        RowMapper<MotorhomeUnitModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeUnitModel.class);
        MotorhomeUnitModel mhu = template.queryForObject(sql, rowMapper, id);
        return mhu;
    }

    /* The method uses a JdbcTemplate object to query the database with information from a
     * MotorhomeUnitModel object, mhu, and updates the entity with the corresponding motorhomeUnitID
     * with that information. */
    public MotorhomeUnitModel updateMotorhomeUnit(MotorhomeUnitModel mhu) {
        String sql = "UPDATE MotorhomeUnit SET registrationYear = ?, drivenKilometers = ?, registration = ?, notes = ? WHERE MotorhomeUnitID = ?";
        template.update(sql, mhu.getRegistrationYear(), mhu.getDrivenKilometers(), mhu.getRegistration(), mhu.getNotes(), mhu.getMotorhomeUnitID());
        return null;
    }

    public List<MotorhomeUnitModel> searchMotorhome(String sd, String ed, String ppd, String nob, String nos, String nosb){
        String sql = "CALL searchAvailableMotorhomeModel02(?, ?, ?, ?, ?, ?)";
        RowMapper<MotorhomeUnitModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeUnitModel.class);
        return template.query(sql, rowMapper, sd, ed, ppd, nob, nos, nosb);
    }

    public List<MotorhomeUnitModel> searchMotorhomeUnit(String ry, String ryf, String ryt, String dk, String dkf, String dkt, String r, String un){
        String sql = "CALL searchMotorhomeUnit(?, ?, ?, ?, ?, ?, ?, ?)";
        RowMapper<MotorhomeUnitModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeUnitModel.class);
        return template.query(sql, rowMapper, ry, ryf, ryt, dk, dkf, dkt, r, un);
    }

    public List<MotorhomeModelModel> searchMotorhomeModel(String b, String m, String nob, String nos, String nosb, String w, String mlw, String ppd, String mdn){
        String sql = "CALL searchMotorhomeModel(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        RowMapper<MotorhomeModelModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeModelModel.class);
        return template.query(sql, rowMapper, b, m, nob, nos, nosb, w, mlw, ppd, mdn);
    }

    /* The method uses a JdbcTemplate object to query the database for motorhome units available at a specified
    * peried and with a specified motorhomeModelID. */
    public List<MotorhomeUnitModel> searchAvailableMotorhomeUnit(String startDate, String endDate, int motorhomeModelID){
        String sql = "CALL searchAvailableMotorhomeUnitsOnDatesAndMotorhomeModelID(?, ?, ?)";
        RowMapper<MotorhomeUnitModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeUnitModel.class);
        return template.query(sql, rowMapper, startDate, endDate, motorhomeModelID);
    }
}
