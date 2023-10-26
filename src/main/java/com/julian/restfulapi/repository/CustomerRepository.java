package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Customer;
import com.julian.restfulapi.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByFirstName(String firstName);

    Optional<Customer> findByEmailIgnoreCase(String name);

  //  List<Customer> findByFirstNameContaining(String firstName);

  //  List<Customer> findByLastNameNotNull();

  //  List<Customer> findByAddress_City(String city);




}
