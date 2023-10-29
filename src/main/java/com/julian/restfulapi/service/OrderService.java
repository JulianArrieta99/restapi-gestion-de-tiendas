package com.julian.restfulapi.service;


import com.julian.restfulapi.entity.Order;
import com.julian.restfulapi.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Page<Order> findOrderByDescriptionContaining(String description, Pageable pageable);


    Order saveOrder(Order order);

    Order updateOrder(Long id, Order order);

    void deleteOrder(Long id);

    List<Order> findAllOrders();

    Order findOrderById(Long id);

    List<Order> findOrdersByStore(Store store);
}