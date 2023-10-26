package com.julian.restfulapi.controller;

import com.julian.restfulapi.controller.dto.ResponseMessage;
import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }


    @GetMapping("/findAllStores")
    public List<Store> findAllStore(){
        return storeService.findAllStores();
    }

    @GetMapping("/findStoreById/{id}")
    Store findLocalById(@PathVariable Long id) {
        return storeService.findStoreById(id);
    }


    @GetMapping("/findStoreByName/{name}")
    Optional<Store> findByNameIgnoreCase(@PathVariable String name){
        return storeService.findByNameIgnoreCase(name);
    }


    @PostMapping("/saveStore")
    public ResponseEntity<ResponseMessage<Store>> saveStore(@Valid @RequestBody Store store){
      Store savedStore = storeService.saveStore(store);
        String message = "Store creado correctamente";
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

}
