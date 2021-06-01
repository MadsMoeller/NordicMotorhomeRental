package com.example.nordicmotorhomes1.Service;

import com.example.nordicmotorhomes1.Model.CustomerModel;
import com.example.nordicmotorhomes1.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerModel> fetchAll(){
        return customerRepository.fetchAll();
    }

    public CustomerModel addCustomer(CustomerModel c){
        return customerRepository.addCustomer(c);
    }

    public CustomerModel findCustomerById(int customerID){
        return customerRepository.findCustomerById(customerID);
    }

    public void updateCustomer(CustomerModel c){
        customerRepository.updateCustomer(c);
    }

    public List<CustomerModel> searchCustomer(String cid, String fn, String ln, String tn, String e, String sa, String an, String zip, String dob, String dln, String dsd){
        return customerRepository.searchCustomer(cid, fn, ln, tn, e, sa, an, zip, dob, dln, dsd);
    }
}
