package com.julian.restfulapi.controller;

import com.julian.restfulapi.entity.Manager;
import com.julian.restfulapi.service.ManagerService;
import com.julian.restfulapi.service.StoreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/findAllManagers")
    public ResponseEntity<List<Manager>> findAllManagers(){
        return ResponseEntity.ok(managerService.findAllManagers());
    }
    @GetMapping("/findManagerById/{id}")
    public ResponseEntity<Optional<Manager>> findManagerById(@PathVariable Long id){
        return ResponseEntity.ok(managerService.findManagerById(id));
    }
    @GetMapping("/findManagerByName/{name}")
    public ResponseEntity<Optional<Manager>> findManagerByName(@PathVariable String name){
        return ResponseEntity.ok(managerService.findManagerByManagerNameIgnoreCase(name));
    }
    @PostMapping("/saveManager")
    public ResponseEntity<Manager> saveManager(@RequestBody Manager managerRequest) {

        return ResponseEntity.ok(managerService.saveManager(managerRequest));

    }
    @PostMapping("/updateManager")
    public ResponseEntity<Manager> updateManager(@PathVariable Long id, @RequestBody Manager manager){
        Manager updatedManager = managerService.updateManager(id,manager);
        return ResponseEntity.ok(updatedManager);
    }
    @DeleteMapping("/deleteManager/{id}")
    public ResponseEntity<String> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.ok("Manager eliminado correctamente");
    }
}
