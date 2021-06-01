package com.example.nordicmotorhomes1.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CustomerModel {
    @Id

    private int customerID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String streetAddress;
    private int addressNumber;
    private String zipCode;
    private String dateOfBirth;
    private int driverLicenseNo;
    private String driverSinceDate;

    public CustomerModel() {
    }

    public CustomerModel(int customerID, String firstName, String lastName, String phone, String email, String streetAddress,
                         int addressNumber, String zipCode, String dateOfBirth, int driverLicenseNo, String driverSinceDate) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.addressNumber = addressNumber;
        this.zipCode = zipCode;
        this.dateOfBirth = dateOfBirth;
        this.driverLicenseNo = driverLicenseNo;
        this.driverSinceDate = driverSinceDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDriverLicenseNo() {
        return driverLicenseNo;
    }

    public void setDriverLicenseNo(int driverLicenseNo) {
        this.driverLicenseNo = driverLicenseNo;
    }

    public String getDriverSinceDate() {
        return driverSinceDate;
    }

    public void setDriverSinceDate(String driverSinceDate) {
        this.driverSinceDate = driverSinceDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", addressNumber=" + addressNumber +
                ", zipcode='" + zipCode + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", driverLicenseNo='" + driverLicenseNo + '\'' +
                ", driverSinceDate='" + driverSinceDate + '\'' +
                '}';
    }
}
