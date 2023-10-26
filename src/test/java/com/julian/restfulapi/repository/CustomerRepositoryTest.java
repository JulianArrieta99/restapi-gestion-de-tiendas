package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CustomerRepositoryTest {
    private final CustomerRepository customerRepository;

    @Autowired
    CustomerRepositoryTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Test
    public void saveCustomer(){
        Customer customer = Customer.builder()
                .firstName("Juslian")
                .lastName("ksaep")
                .email("juslian@mail.com")
                .build();
        customerRepository.save(customer);
    }
    @Test
    public void saveCustomerWithAddressEmbedded(){
        Customer customer = Customer.builder()
                .firstName("Juslian")
                .lastName("ksaep")
                .email("juslian@mail.com")
                .build();
        customerRepository.save(customer);
    }

    @Test
    public void findCustomerByFirstName(){
        Customer customer = customerRepository.findByFirstName("Julian").get();
        System.out.println("customer = " + customer);
    }

    @Test
    public void findAllCustomers(){
        List<Customer> customerList =  customerRepository.findAll();
        System.out.println("customerList = " + customerList);
    }
}