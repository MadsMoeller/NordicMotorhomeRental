package com.example.nordicmotorhomes1.Repository;

import com.example.nordicmotorhomes1.Model.BookingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingRepository {

    @Autowired
    JdbcTemplate template;

    /* The method uses a JdbcTemplate object to query the database to return a list of all bookings
     * stored there. The bookings are stored by start date first, and end date second, meaning the
     * booking that starts the earliest will be shown first, and if two bookings start at the same day,
     * the one that finishes first will be shown first. */
    public List<BookingModel> fetchAll(){
        String sql = "SELECT * FROM booking ORDER BY startDate, endDate";
        RowMapper<BookingModel> rowMapper = new BeanPropertyRowMapper<>(BookingModel.class);
        return template.query(sql, rowMapper);
    }

    /* The method uses a JdbcTemplate object to query the database to return the booking with the
    * highest bookingID. Due to how the database is set up to autoincrement bookingIDs whenever a
    * new booking is added, the method will return the newest booking added. */
    public BookingModel fetchLatest(){
        String sql = "SELECT * FROM booking WHERE bookingID = (SELECT max(bookingID) FROM booking)";
        RowMapper<BookingModel> rowMapper = new BeanPropertyRowMapper<>(BookingModel.class);
        BookingModel b = template.queryForObject(sql, rowMapper);
        return b;
    }

    /* The method uses a JdbcTemplate object to insert a Booking entity into the database with
     * the data corresponding to the BookingModel object taken as parameter. */
    public BookingModel addBooking(BookingModel b){
        String sql = "INSERT INTO booking (" +
                "bookingID, " +
                "startDate, " +
                "endDate, " +
                "pickupTime, " +
                "pickupPlace, " +
                "dropoffTime, " +
                "dropoffPlace, " +
                "customerID, " +
                "motorhomeUnitID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(sql,
                b.getBookingID(),
                b.getStartDate(),
                b.getEndDate(),
                b.getPickupTime(),
                b.getPickupPlace(),
                b.getDropoffTime(),
                b.getDropoffPlace(),
                b.getCustomerID(),
                b.getMotorhomeUnitID());

        return null;
    }

    /* The method uses a JdbcTemplate object to query the database returning a BookingModel
     * object mathcing the entity with the corresponding bookingID. */
    public BookingModel findBookingById(int bookingID){
        String sql = "SELECT * FROM booking WHERE bookingID = ?";
        RowMapper<BookingModel> rowMapper = new BeanPropertyRowMapper<>(BookingModel.class);
        BookingModel b = template.queryForObject(sql, rowMapper, bookingID);
        return b;
    }

    /* The method uses a JdbcTemplate object to query the database with information from a
     * BookingModel object, b, and updates the entity with the corresponding bookingID
     * with that information. */
    public BookingModel updateBooking(int bookingID, BookingModel b){
        String sql = "UPDATE booking SET " +
                "bookingID = ?, " +
                "startDate = ?, " +
                "endDate = ?, " +
                "pickupTime = ?," +
                "pickupPlace = ?," +
                "dropoffTime = ?," +
                "dropoffPlace = ?," +
                "customerID = ?," +
                "motorhomeUnitID = ?" +
                " WHERE bookingID = ?";

        template.update(sql,
                b.getBookingID(),
                b.getStartDate(),
                b.getEndDate(),
                b.getPickupTime(),
                b.getPickupPlace(),
                b.getDropoffTime(),
                b.getDropoffPlace(),
                b.getCustomerID(),
                b.getMotorhomeUnitID(),
                bookingID);

        return null;
    }

    /* The method uses a JdbcTemplate object to query the database for a list of bookings matching
     * the criteria specified in the parameters. */
    public List<BookingModel> searchBookings(String fromDate, String toDate, String bookingID, String customerID, String motorhomeUnitID){
        String sql = "CALL searchBooking(?, ?, ?, ?, ?)";
        RowMapper<BookingModel> rowMapper = new BeanPropertyRowMapper<>(BookingModel.class);
        return template.query(sql, rowMapper, fromDate, toDate, bookingID, customerID, motorhomeUnitID);
    }

}
