package com.julian.restfulapi.service.impl;

import com.julian.restfulapi.entity.Customer;
import com.julian.restfulapi.error.local.CustomerNotFoundException;
import com.julian.restfulapi.error.local.ManagerNotFoundException;
import com.julian.restfulapi.repository.CustomerRepository;
import com.julian.restfulapi.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository ) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElseThrow(() -> new CustomerNotFoundException("El customer con el id proporcionado no fue encontrado"));
    }

    @Override
    public Optional<Customer> findByEmailIgnoreCase(String name) {
        return customerRepository.findByEmailIgnoreCase(name);
    }

    @Transactional
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();

            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPassword(customer.getPassword());


            return customerRepository.save(existingCustomer);
        } else {
            throw new ManagerNotFoundException("Customer con ID " + id + " no encontrado");
        }

    }



    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


}
