package com.example.nordicmotorhomes1.Repository;

import com.example.nordicmotorhomes1.Model.MotorhomeModelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorhomeModelRepository {

    @Autowired
    JdbcTemplate template;

    /* The method uses a JdbcTemplate object to query the database to return a list of all motorhome models
    * stored there. */
    public List<MotorhomeModelModel> fetchAll(){
        String sql = "SELECT * FROM motorhomeModel";
        RowMapper<MotorhomeModelModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeModelModel.class);
        return template.query(sql, rowMapper);
    }

    /* The method uses a JdbcTemplate object to insert a MoterhomeModel entity into the database with
    * the data corresponding to the MoterhomeModelModel object taken as parameter. */
    public MotorhomeModelModel addMotorhomeModel(MotorhomeModelModel mm){
        String sql = "INSERT INTO motorhomeModel (" +
                "motorhomeModelID, " +
                "brand, " +
                "modelName, " +
                "pricePerDay, " +
                "numberOfBeds, " +
                "numberOfSeats, " +
                "numberOfSeatBelts, " +
                "weight, " +
                "maxLoadedWeight, " +
                "descriptionNote) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(sql,
                mm.getMotorhomeModelID(),
                mm.getBrand(),
                mm.getModelName(),
                mm.getPricePerDay(),
                mm.getNumberOfBeds(),
                mm.getNumberOfSeats(),
                mm.getNumberOfSeatBelts(),
                mm.getWeight(),
                mm.getMaxLoadedWeight(),
                mm.getDescriptionNote());

        return null;
    }

    /* The method uses a JdbcTemplate object to query the database returning a MotorhomeModelModel
    * object mathcing the entity with the corresponding motorhomeModelID. */
    public MotorhomeModelModel findMotorhomeModelById(int motorhomeModelID){
        String sql = "SELECT * FROM motorhomeModel WHERE motorhomeModelID = ?";
        RowMapper<MotorhomeModelModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeModelModel.class);
        MotorhomeModelModel mm = template.queryForObject(sql, rowMapper, motorhomeModelID);
        return mm;
    }

    /* The method uses a JdbcTemplate object to query the database with information from a
    * MotorhomeModelModel object, mm, and updates the entity with the corresponding motorhomeModelID 
    * with that information. */
    public MotorhomeModelModel updateMotorhomeModel(int motorhomeModelID, MotorhomeModelModel mm){
        String sql = "UPDATE motorhomeModel SET " +
                "motorhomeModelID = ?, " +
                "brand = ?, " +
                "modelName = ?, " +
                "pricePerDay = ?, " +
                "numberOfBeds = ?, " +
                "numberOfSeats = ?, " +
                "numberOfSeatBelts = ?, " +
                "weight = ?, " +
                "maxLoadedWeight = ?, " +
                "descriptionNote = ? " +
                "WHERE motorhomeModelID = ?";

        template.update(sql,
                mm.getMotorhomeModelID(),
                mm.getBrand(),
                mm.getModelName(),
                mm.getPricePerDay(),
                mm.getNumberOfBeds(),
                mm.getNumberOfSeats(),
                mm.getNumberOfSeatBelts(),
                mm.getWeight(),
                mm.getMaxLoadedWeight(),
                mm.getDescriptionNote(),
                motorhomeModelID);

        return null;
    }

    /* The method uses a JdbcTemplate object to query the database for MotorhomeModelModels available
    * in a specified time frame (sd and ed) matching certain criteria. A List<MotorhomeModelModel> with
    * elements matching those criteria is returned. */
    public List<MotorhomeModelModel> searchMotorhomeModel(String sd, String ed, String ppd, String br, String mod, String nob, String nos, String nosb){
        String sql = "CALL searchAvailableMotorhomeModel(?, ?, ?, ?, ?, ?, ?, ?)";
        RowMapper<MotorhomeModelModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeModelModel.class);
        return template.query(sql, rowMapper, sd, ed, ppd, br, mod, nob, nos, nosb);
    }

    /* The method uses a JdbcTemplate object to query the database for MotorhomeModelModels
     * matching certain criteria. A List<MotorhomeModelModel> with
     * elements matching those criteria is returned. */
    public List<MotorhomeModelModel> searchMotorhomeModelParam(String br, String mod, String ppd, String nob, String nos, String nosb, String w, String mlw, String mdn) {
        String sql = "CALL searchMotorhomeModel(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        RowMapper<MotorhomeModelModel> rowMapper = new BeanPropertyRowMapper<>(MotorhomeModelModel.class);
        return template.query(sql, rowMapper, br, mod, nob, nos, nosb, w, mlw, ppd, mdn);
    }
}
