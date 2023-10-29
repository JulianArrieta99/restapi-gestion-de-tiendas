package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Order;
import com.julian.restfulapi.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Page<Order> findOrderDescriptionContainingByStore(String description, Pageable pageable);

    List<Order> findOrdersByStore(Store store);
}
