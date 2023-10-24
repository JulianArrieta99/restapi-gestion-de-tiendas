package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Address;
import com.julian.restfulapi.entity.Customer;
import org.antlr.v4.runtime.atn.LL1Analyzer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
        Address address = Address.builder()
                .city("Buenos Aires")
                .mainStreet("Av. Corrientes")
                .secondaryStreet("Av. cabildo")
                .build();
        Customer customer = Customer.builder()
                .firstName("Juslian")
                .lastName("ksaep")
                .email("juslian@mail.com")
                .address(address)
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

    @Test
    public void findAllCustomersFirstNameContaining(){
        List<Customer> customerList = customerRepository.findByFirstNameContaining("e");
        System.out.println("customerList = " + customerList);
    }
    @Test
    public void findAllCustomersLastNameNotNull(){
        List<Customer> customerList = customerRepository.findByLastNameNotNull();
        System.out.println("customerList = " + customerList);
    }
    @Test
    public void findAllCustomersByAddressCity(){
        List<Customer> customerList = customerRepository.findByAddress_City("buenos aires");
        customerList.forEach(System.out::println);
    }
    @Test
    public void getCustomerByEmailAddress(){
        Customer customer = customerRepository.getCustomerByEmailAddress("carlo@mail.com");
        System.out.println("customer = " + customer);
    }

    @Test
    public void getCustomerFirstNameByEmailAddress(){
        String firstName = customerRepository.getCustomerFirstNameByEmailAddress("carlo@mail.com");
        System.out.println("firstName = " + firstName);
    }

    @Test
    public void getCustomerByEmailAddressNative(){
        Customer customer = customerRepository.getCustomerByEmailAddressNative("manuel@mail.com");
        System.out.println("customer = " + customer);
    }

    @Test
    public void getCustomerByEmailAddressNativeNamedParam(){
        Customer customer = customerRepository.getCustomerByEmailAddressNativeNamedParam("manuel@mail.com");
        System.out.println("customer = " + customer);
    }
    @Test
    public void updateCustomerNameByEmail(){
        customerRepository.updateCustomerNameByEmail("jose", "jose@mail.com");
    }
    @Test // es igual que el getCustomerByEmail pero sin @Query
    public void findCustomerByEmail(){
      Customer customer = customerRepository.findByEmail("carlo@gmail.com").get();
        System.out.println("customer = " + customer);
        
    }
}