package com.julian.restfulapi.controller;

import com.julian.restfulapi.controller.dto.ResponseMessage;
import com.julian.restfulapi.entity.Order;
import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.entity.dto.OrderDTO;
import com.julian.restfulapi.entity.mapper.OrderMapper;
import com.julian.restfulapi.service.OrderService;
import com.julian.restfulapi.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final StoreService storeService;

    @Autowired
    public OrderController(OrderService orderService, StoreService storeService) {
        this.orderService = orderService;
        this.storeService = storeService;
    }


    @GetMapping("/findAllOrders")
    public List<Order> findAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/findOrderById/{id}")
    Order findLocalById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    // el size de la pagina por default es 20 si el cliente no envia datos al pageable
    // parametros que puede enviar el cliente
    // /api/orders/findOrderByDescription?description=mi_descripcion&page=0&size=10&sort=propertyName,asc
   /* description=mi_descripcion es el parámetro de búsqueda.
    page=0 indica que se debe recuperar la primera página de resultados.
    size=10 indica que cada página debe contener 10 elementos.
            sort=propertyName,asc ordena los resultados por la propiedad propertyName en orden ascendente.
    */
    @GetMapping("/findOrderByDescription")
    public ResponseEntity<Page<Order>> findOrderByDescription(
            @RequestParam("description") String description,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<Order> orders = orderService.findOrderByDescriptionContaining(description, pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/findOrdersByStore/{Id}")
    public ResponseEntity<List<Order>> findOrdersByStore(@PathVariable Long Id) {
        Store store = storeService.findStoreById(Id); // Supongamos que tienes un servicio para buscar una tienda por su ID
        if (store == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList()); // Devuelve una lista vacía y código de estado 404
        }
        List<Order> ordersByStore = orderService.findOrdersByStore(store);
        if (ordersByStore.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList()); // Devuelve una lista vacía y código de estado 204
        }

        return ResponseEntity.ok(ordersByStore); // Devuelve la lista de órdenes y código de estado 200 OK
    }

    @Transactional
    @PostMapping("/saveOrder")
    public ResponseEntity<ResponseMessage<Order>> saveOrder(@Valid @RequestBody OrderDTO orderRequest) {
        Store store = storeService.findStoreById(orderRequest.getStoreId());

        if (store == null) {
            String errorMessage = "No se encontró la tienda con ID: " + orderRequest.getStoreId();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(errorMessage, null));
        }

        Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderRequest);
        order.setStore(store);
        Order savedOrder = orderService.saveOrder(order);
        String message = "Order creada correctamente";
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message, savedOrder));
    }

        @Transactional
        @PutMapping("/updateOrder/{id}")
        public ResponseEntity<ResponseMessage<Order>> updateOrder (@PathVariable Long id, @RequestBody Order order){
            Order updatedOrder = orderService.updateOrder(id, order);
            String message = "Order actualizada correctamente";
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message, updatedOrder));
        }

        @Transactional
        @DeleteMapping("/deleteOrder/{id}")
        public ResponseEntity<String> deleteOrder (@PathVariable Long id){
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Order eliminada correctamente");
        }

    }
