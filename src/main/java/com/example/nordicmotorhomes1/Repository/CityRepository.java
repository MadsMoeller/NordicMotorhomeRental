package com.example.nordicmotorhomes1.Repository;

import com.example.nordicmotorhomes1.Model.CityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepository {

    @Autowired
    JdbcTemplate template;

    /* The method uses a JdbcTemplate object to query the database returning a CityModel
     * object mathcing the entity with the corresponding zipcode. */
    public CityModel findCityByZipcode(String zipcode){
        String sql = "SELECT * FROM city WHERE zipcode = ?";
        RowMapper<CityModel> rowMapper = new BeanPropertyRowMapper<>(CityModel.class);
        CityModel c = template.queryForObject(sql, rowMapper, zipcode);
        return c;
    }
}
