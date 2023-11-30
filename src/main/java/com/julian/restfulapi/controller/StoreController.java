package com.julian.restfulapi.controller;

import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.service.StoreService;
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
    public List<Store> findAllStore() {
        return storeService.findAllStores();
    }

    @GetMapping("/findStoreById/{id}")
    ResponseEntity<Store> findLocalById(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.findStoreById(id));
    }


    @GetMapping("/findStoreByName/{name}")
    ResponseEntity<Optional<Store>> findByNameIgnoreCase(@PathVariable String name){
        return ResponseEntity.ok( storeService.findByNameIgnoreCase(name));
    }

    @PostMapping("/saveStore")
    public ResponseEntity<Store> saveStore(@RequestBody Store store){
      Store savedStore = storeService.saveStore(store);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStore);
    }

    @PutMapping("/updateStore/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody Store store){
        Store updatedStore =  storeService.updateStore(id, store);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedStore);
    }

    @DeleteMapping("/deleteStore/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("Store eliminado correctamente");
    }

}
