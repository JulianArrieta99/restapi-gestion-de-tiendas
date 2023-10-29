package com.julian.restfulapi.service;


import com.julian.restfulapi.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAllCustomers();
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer local);
    Customer findCustomerById(Long id);
    Optional<Customer> findByEmailIgnoreCase(String name);
    void deleteCustomer(Long id);

}