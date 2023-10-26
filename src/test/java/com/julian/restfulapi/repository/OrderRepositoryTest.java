package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.entity.Manager;
import com.julian.restfulapi.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class OrderRepositoryTest {
    private final OrderRepository orderRepository;

    @Autowired
    OrderRepositoryTest(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Test
    public void saveOrder(){
        Manager manager = Manager.builder()
                .firstName("Arturo")
                .lastName("Dorado")
                .build();
        Store store = Store.builder()
                .name("Binco")
                .floor("Fourth floor")
                .manager(manager)
                .build();

        Order order = Order.builder()
                .description("Camisa de tirantes blanca")
                .price(10.0)
                .store(store)
                .build();
        orderRepository.save(order);
        System.out.println("order = " + order);
    }

    @Test
    public void findAllOrdersPaging(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        List<Order> ordersList = orderRepository.findAll(firstPageWithThreeRecords).getContent();
        long totalElements = orderRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        long totalPages = orderRepository.findAll(firstPageWithThreeRecords).getTotalPages();
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("ordersList = " + ordersList);

    }
    @Test
    public void findAllOrdersWithSorting(){
        Pageable sortByPriceASC = PageRequest.of(0, 3, Sort.by("price"));
        Pageable sortByPriceDESC = PageRequest.of(0, 3, Sort.by("price").descending());
        List<Order> orderListASC = orderRepository.findAll(sortByPriceASC).getContent();
        List<Order> orderListDESC = orderRepository.findAll(sortByPriceDESC).getContent();
        System.out.println("orderListASC = " + orderListASC);
        System.out.println("orderListASC = " + orderListDESC);

    }
    @Test
    public void findAllOrdersDescriptionContaining(){
        Pageable pageable = PageRequest.of(0,5);
        List<Order> orderList = 
                orderRepository.findByDescriptionContaining("En", pageable).getContent();
        System.out.println("orderList = " + orderList);
    }
}