package com.julian.restfulapi.controller;

import com.julian.restfulapi.entity.Customer;
import com.julian.restfulapi.entity.dto.CustomerDTO;
import com.julian.restfulapi.entity.mapper.CustomerMapper;
import com.julian.restfulapi.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/findAllCustomers")
    public ResponseEntity<List<CustomerDTO>> findAllCustomers(){
        List<Customer> customers = customerService.findAllCustomers();
        List<CustomerDTO> customerDTO = CustomerMapper.INSTANCE.customersToCustomerDTOs(customers);
        return ResponseEntity.ok(customerDTO);
    }
    @GetMapping("/findCustomerById/{id}")
    public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id);
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
        return ResponseEntity.ok(customerDTO);
    }
    @GetMapping("/findCustomerByEmail/{name}")
    Optional<Customer> findCustomerByEmail(@PathVariable String name){
        return customerService.findByEmailIgnoreCase(name);
    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer){
        Customer savedCustomer =  customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @PathVariable Long id, @RequestBody Customer customer){
        Customer updatedCustomer = customerService.updateCustomer(id,customer);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
    }
    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }

}
