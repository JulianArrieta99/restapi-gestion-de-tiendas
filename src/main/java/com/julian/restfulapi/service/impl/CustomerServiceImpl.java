package com.julian.restfulapi.service.impl;

import com.julian.restfulapi.controller.dto.ResponseMessage;
import com.julian.restfulapi.entity.Customer;
import com.julian.restfulapi.error.local.CustomerNotFoundException;
import com.julian.restfulapi.repository.CustomerRepository;
import com.julian.restfulapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return customerOptional.orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public Optional<Customer> findByEmailIgnoreCase(String name) {
        return customerRepository.findByEmailIgnoreCase(name);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        customerOptional.ifPresentOrElse(
                existingCustomer -> {
                    // Actualizar los campos del existingCustomer con los valores del customer recibido como parámetro
                    existingCustomer.setFirstName(customer.getFirstName());
                    existingCustomer.setLastName(customer.getLastName());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setPassword(customer.getPassword());
                    // ... (actualiza otros campos según sea necesario)

                    // Guardar el existingCustomer actualizado en la base de datos
                    customerRepository.save(existingCustomer);
                },
                () -> {
                    // El Customer con el ID dado no existe en la base de datos, manejar según tus requerimientos, por ejemplo, lanzar una excepción
                    throw new CustomerNotFoundException(id);

                }
        );

        return customerOptional.orElse(null); // devolver el Customer actualizado
    }



    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


}
