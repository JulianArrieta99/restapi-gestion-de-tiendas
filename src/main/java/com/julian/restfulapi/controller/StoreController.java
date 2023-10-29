package com.julian.restfulapi.controller;

import com.julian.restfulapi.controller.dto.ResponseMessage;
import com.julian.restfulapi.entity.Order;
import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.entity.dto.StoreDTO;
import com.julian.restfulapi.entity.mapper.StoreMapper;
import com.julian.restfulapi.service.OrderService;
import com.julian.restfulapi.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;
    private final OrderService orderService;

    @Autowired
    public StoreController(StoreService storeService, OrderService orderService) {
        this.storeService = storeService;
        this.orderService = orderService;
    }


    @GetMapping("/findAllStores")
    public List<StoreDTO> findAllStore() {
        List<Store> stores = storeService.findAllStores();
        return stores.stream()
                .map(StoreMapper.INSTANCE::storeToStoreDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/findStoreById/{id}")
    Store findLocalById(@PathVariable Long id) {
        return storeService.findStoreById(id);
    }


    @GetMapping("/findStoreByName/{name}")
    Optional<Store> findByNameIgnoreCase(@PathVariable String name){
        return storeService.findByNameIgnoreCase(name);
    }

    @Transactional
    @PostMapping("/saveStore")
    public ResponseEntity<ResponseMessage<Store>> saveStore(@Valid @RequestBody Store store){
      Store savedStore = storeService.saveStore(store);
        String message = "Store creada correctamente";
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message, savedStore));
    }

    @PutMapping("/updateStore/{id}")
    public ResponseEntity<ResponseMessage<Store>> updateStore(@PathVariable Long id, @RequestBody Store store){
        Store updatedStore =  storeService.updateStore(id, store);
        String message = "Store guardado correctamente";
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message, updatedStore));
    }

    @DeleteMapping("/deleteStore/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("Store eliminado correctamente");
    }

    @GetMapping("/storeOrders/{storeId}")
    public ResponseEntity<List<Order>> getOrdersByStore(@PathVariable Long storeId) {
        Store store = storeService.findStoreById(storeId);

        List<Order> orders = orderService.findOrdersByStore(store);

        return ResponseEntity.ok(orders);
    }
}
