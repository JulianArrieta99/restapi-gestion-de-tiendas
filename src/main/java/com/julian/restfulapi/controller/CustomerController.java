package com.julian.restfulapi.controller;

import com.julian.restfulapi.entity.Customer;
import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.entity.dto.CustomerDTO;
import com.julian.restfulapi.entity.mapper.CustomerMapper;
import com.julian.restfulapi.controller.dto.ResponseMessage;
import com.julian.restfulapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.julian.restfulapi.entity.Role.USER;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final MessageSource messageSource;

    @Autowired
    public CustomerController(CustomerService customerService, MessageSource messageSource) {
        this.customerService = customerService;
        this.messageSource = messageSource;
    }

    @GetMapping("/findAllCustomers")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<CustomerDTO>> findAllCustomers(){
        List<Customer> customers = customerService.findAllCustomers();
        List<CustomerDTO> customerDTO = CustomerMapper.INSTANCE.customersToCustomerDTOs(customers);
        return ResponseEntity.ok(customerDTO);
    }
    @GetMapping("/findCustomerById/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id);
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
        return ResponseEntity.ok(customerDTO);
    }
    @GetMapping("/findCustomerByEmail/{name}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    Optional<Customer> findCustomerByEmail(@PathVariable String name){
        return customerService.findByEmailIgnoreCase(name);
    }

    @PostMapping("/saveCustomer")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ResponseMessage<Customer>> saveCustomer( @RequestBody Customer customer){
        if(customer.getRole() == null){
            customer.setRole(USER);
        }
        Customer savedCustomer =  customerService.saveCustomer(customer);
        String message = "Cliente creado correctamente";
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message, savedCustomer));
    }

    @PutMapping("/updateCustomer/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ResponseMessage<Customer>> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        Customer updatedCustomer = customerService.updateCustomer(id,customer);
        String message = "Cliente actualizado correctamente";
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, updatedCustomer));
    }
    @DeleteMapping("/deleteCustomer/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }

}
