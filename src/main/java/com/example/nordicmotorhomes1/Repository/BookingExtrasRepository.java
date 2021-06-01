package com.example.nordicmotorhomes1.Repository;

import com.example.nordicmotorhomes1.Model.BookingExtrasModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingExtrasRepository {
    @Autowired
    JdbcTemplate template;

    /* The method uses a JdbcTemplate object to query the database to return a list of all bookingextras
     * stored there. */
    public List<BookingExtrasModel> fetchAll(){
        String sql = "SELECT * FROM bookingextras";
        RowMapper<BookingExtrasModel> rowMapper = new BeanPropertyRowMapper<>(BookingExtrasModel.class);
        return template.query(sql, rowMapper);
    }

    /* The method uses a JdbcTemplate object to insert a BookingExtras entity into the database with
     * the data corresponding to the BookingExtrasModel object taken as parameter. */
    public BookingExtrasModel addBookingExtras(BookingExtrasModel be){
        String sql = "INSERT INTO bookingextras (bookingid, extraid, amount) VALUES (?, ?, ?)";
        template.update(sql, be.getBookingID(), be.getExtraID(), be.getAmount());
        return null;
    }

    /* The method uses a JdbcTemplate object to query the database returning a BookingExtrasModel
     * object mathcing the entity with the corresponding bookingID and extraID. */
    public BookingExtrasModel findBookingExtrasByIds(int bookingID, int extraID){
        String sql = "SELECT * FROM bookingextras WHERE bookingID = ? AND extraID = ?";
        RowMapper<BookingExtrasModel> rowMapper = new BeanPropertyRowMapper<>(BookingExtrasModel.class);
        BookingExtrasModel be = template.queryForObject(sql, rowMapper, bookingID, extraID);
        return be;
    }

    /* The method uses a JdbcTemplate object to query the database to delete bookingextras entities
    * with bookingID and extraID matching those specified in the parameters. */
    public Boolean deleteBookingExtras(int bookingID, int extraID){
        String sql = "DELETE FROM bookingextras WHERE bookingID = ? AND extraID = ?";
        return template.update(sql, bookingID, extraID) > 0;
    }

    /* The method uses a JdbcTemplate object to query the database to delete bookingextras entities
    * with an amount of 0. */
    public void deleteBookingExtrasWithZeroAmount(){
        String sql = "DELETE FROM bookingextras WHERE amount = 0";
        template.update(sql);
    }

    /* The method uses a JdbcTemplate object to query the database to return a list of all bookingextras
     * with a certain bookingID. */
    public List<BookingExtrasModel> fetchAllByBookingId(int bookingID){
        String sql = "SELECT * FROM bookingextras WHERE bookingID = ?";
        RowMapper<BookingExtrasModel> rowMapper = new BeanPropertyRowMapper<>(BookingExtrasModel.class);
        return template.query(sql, rowMapper, bookingID);
    }
}
