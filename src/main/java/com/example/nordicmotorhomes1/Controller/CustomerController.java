package com.example.nordicmotorhomes1.Controller;

import com.example.nordicmotorhomes1.Model.CustomerModel;
import com.example.nordicmotorhomes1.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /* The method gets a list with information of all customers from the database, and
    * displays it on a HTML site. */
    @GetMapping("/customer")
    public String customer(Model model){
        List<CustomerModel> customerList = customerService.fetchAll();
        model.addAttribute("customerModels", customerList);
        return "Customer/customer";
    }

    /* The HTML site this method refers to prompts the user for information about a new customer. */
    @GetMapping("/createCustomer")
    public String createCustomer(){
        return "Customer/createCustomer";
    }

    /* This method takes information the user inputted about a new customer. A CustomerModel object
    * is instantiated. The object is used to save information about the customer in the database.
    * A list with information about all customers is fetched from the database and displayed on a new
    * HTML site. */
    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute CustomerModel customer, Model model){
        customerService.addCustomer(customer);
        List<CustomerModel> customerList = customerService.fetchAll();
        model.addAttribute("customerModels", customerList);
        return "Customer/customer";
    }

    /* The method takes a customerID in the URL and finds the corresponding customer entity in the
    * database. The HTML page promts the user for updating the information from that customer. */
    @GetMapping("/Customer/updateCustomer/{customerID}")
    public String updateCustomer(@PathVariable("customerID") int customerID, Model model){
        model.addAttribute("customer", customerService.findCustomerById(customerID));
        return "Customer/updateCustomer";
    }

    /* The method takes the information about the customer, which the user just inputted, and updates
    * the existing vustomer entity in the database. */
    @PostMapping("/Customer/updateCustomer")
    public String updateCustomer(@ModelAttribute CustomerModel customer){
        customerService.updateCustomer(customer);
        return "redirect:/customer";
    }

    /* The method takes parameters inputted inputted and queries the database to returns a list
    * of CustomerModel objects that matches the the inputted search parameters. */
    @PostMapping("/searchCustomer")
    public String searchCustomer(
            @RequestParam(value = "customerID", required = false) String cid,
            @RequestParam(value = "firstName", required = false) String fn,
            @RequestParam(value = "lastName", required = false) String ln,
            @RequestParam(value = "telephoneNumber", required = false) String tn,
            @RequestParam(value = "email", required = false) String e,
            @RequestParam(value = "streetAddress", required = false) String sa,
            @RequestParam(value = "addressNumber", required = false) String an,
            @RequestParam(value = "zipcode", required = false) String zip,
            @RequestParam(value = "dateOfBirth", required = false) String dob,
            @RequestParam(value = "driverLicenseNo", required = false) String dln,
            @RequestParam(value = "driverSinceDate", required = false) String dsd,
            Model model
    ){
        List<CustomerModel> customerList = customerService.searchCustomer(cid, fn, ln, tn, e, sa, an, zip, dob, dln, dsd);
        model.addAttribute("customerModels", customerList);
        return "Customer/customer";
    }
}
