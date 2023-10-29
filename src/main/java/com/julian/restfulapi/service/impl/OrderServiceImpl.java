package com.julian.restfulapi.service.impl;

import com.julian.restfulapi.entity.Order;
import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.repository.OrderRepository;
import com.julian.restfulapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> findOrdersByStore(Store store) {
        return orderRepository.findOrdersByStore(store);
    }

    @Override
    public Page<Order> findOrderByDescriptionContaining(String description, Pageable pageable) {
        return orderRepository.findOrderDescriptionContainingByStore(description, pageable);
    }




    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        Order orderDb = orderRepository.findById(id).get();
        return orderRepository.save(orderDb);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }


}
