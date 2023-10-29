package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
    public class CustomerRepositoryMockitoTest {

        @Mock
        private CustomerRepository customerRepository;

        @Test
        public void findByEmailIgnoreCase() {
            Customer customer = Customer.builder()
                    .customerId(1L)
                    .firstName("Julian")
                    .lastName("ksaep")
                    .email("julian2522@mail.com")
                    .password("12345")
                    .role(Role.USER)
                    .build();
            when(customerRepository.findByEmailIgnoreCase("julian2522@mail.com")).thenReturn(Optional.ofNullable(customer));

            // Llama al método que quieres testear.
            Optional<Customer> customerFound = customerRepository.findByEmailIgnoreCase("julian2522@mail.com");

            // Verifica que se devuelva el objeto `Customer` esperado.
            assertEquals(customer, customerFound.get());
        }
    }
