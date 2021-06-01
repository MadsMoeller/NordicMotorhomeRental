package com.example.nordicmotorhomes1.Repository;

import com.example.nordicmotorhomes1.Model.ExtraModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExtraRepository {

    @Autowired
    JdbcTemplate template;

    /* The method uses a JdbcTemplate object to query the database to return a list of all extra items
     * stored there. */
    public List<ExtraModel> fetchAll(){
        String sql = "SELECT * FROM extras";
        RowMapper<ExtraModel> rowMapper = new BeanPropertyRowMapper<>(ExtraModel.class);
        return template.query(sql, rowMapper);
    }

    /* The method uses a JdbcTemplate object to query the database to return a list of all extra items
     * that are connected to a certain booking via bookingextras. */
    public List<ExtraModel> fetchAllByBookingId(int bookingID){
        String sql = "SELECT * FROM extras WHERE extraID IN (" +
                "SELECT extraID FROM bookingextras WHERE bookingID = ?" +
                ")";
        RowMapper<ExtraModel> rowMapper = new BeanPropertyRowMapper<>(ExtraModel.class);
        return template.query(sql, rowMapper, bookingID);
    }
}
