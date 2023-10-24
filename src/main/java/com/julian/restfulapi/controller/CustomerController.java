package com.julian.restfulapi.controller;

import com.julian.restfulapi.entity.Customer;
import com.julian.restfulapi.entity.Local;
import com.julian.restfulapi.service.CustomerService;
import com.julian.restfulapi.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/findCustomerById/{id}")
    Customer findCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/findAllCustomers")
    public List<Customer> findAllCustomers(){
        return customerService.findAllCustomers();
    }

    @PostMapping("/saveCustomer")
    public Customer saveCustomer( @RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/updateCustomer/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        return customerService.updateCustomer(id,customer);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Successfully deleted";
    }

}
