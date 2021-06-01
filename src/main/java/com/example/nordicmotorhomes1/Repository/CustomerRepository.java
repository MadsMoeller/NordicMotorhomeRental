package com.example.nordicmotorhomes1.Repository;

import com.example.nordicmotorhomes1.Model.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CustomerRepository {
    @Autowired
    JdbcTemplate template;

    /* The method uses a JdbcTemplate object to query the database to return a list of all customers
     * stored there. */
    public List<CustomerModel> fetchAll() {
        String sql = "SELECT * FROM Customer";
        RowMapper<CustomerModel> rowMapper = new BeanPropertyRowMapper<>(CustomerModel.class);
        return template.query(sql, rowMapper);
    }

    /* The method uses a JdbcTemplate object to insert a Customer entity into the database with
     * the data corresponding to the CustomerModel object taken as parameter. */
    public CustomerModel addCustomer(CustomerModel c){
        String sql = "INSERT INTO Customer (customerID, firstName, lastName, phone, email, streetAddress, addressNumber, zipCode, dateOfBirth, driverLicenseNo, driverSinceDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, c.getCustomerID(), c.getFirstName(), c.getLastName(), c.getPhone(), c.getEmail(), c.getStreetAddress(),c.getAddressNumber(), c.getZipCode(), c.getDateOfBirth(),
                c.getDriverLicenseNo(), c.getDriverSinceDate());
        return null;
    }

    /* The method uses a JdbcTemplate object to query the database returning a CustomerModel
     * object mathcing the entity with the corresponding customerID. */
    public CustomerModel findCustomerById(int customerID){
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        RowMapper<CustomerModel> rowMapper = new BeanPropertyRowMapper<>(CustomerModel.class);
        CustomerModel c = template.queryForObject(sql, rowMapper, customerID);
        return c;
    }

    /* The method uses a JdbcTemplate object to query the database with information from a
     * CustomerModel object, c, and updates the entity with the corresponding customerID
     * with that information. */
    public void updateCustomer(CustomerModel c){
        String sql = "UPDATE Customer SET firstName = ?, lastName = ?, phone = ?, email = ?, streetAddress = ?, addressNumber = ?, zipCode = ?, dateOfBirth = ?, driverLicenseNo = ?, driverSinceDate = ? WHERE customerID = ?";
        template.update(sql, c.getFirstName(), c.getLastName(), c.getPhone(), c.getEmail(), c.getStreetAddress(),c.getAddressNumber(), c.getZipCode(), c.getDateOfBirth(),
                c.getDriverLicenseNo(), c.getDriverSinceDate(), c.getCustomerID());
    }

    /* The method uses a JdbcTemplate object to query the database for a list of customers matching
    * the criteria specified in the parameters. */
    public List<CustomerModel> searchCustomer(String cid, String fn, String ln, String tn, String e, String sa, String an, String zip, String dob, String dln, String dsd){
        String sql = "CALL searchCustomer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        RowMapper rowMapper = new BeanPropertyRowMapper(CustomerModel.class);
        return template.query(sql, rowMapper, cid, fn, ln, tn, e, sa, an, zip, dob, dln, dsd);
    }
}