package com.julian.restfulapi.controller;

import com.julian.restfulapi.controller.dto.ResponseMessage;
import com.julian.restfulapi.entity.Manager;
import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.entity.dto.ManagerDTO;
import com.julian.restfulapi.entity.mapper.ManagerMapper;
import com.julian.restfulapi.service.ManagerService;
import com.julian.restfulapi.service.StoreService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    private final ManagerService managerService;
    private final StoreService storeService;

    @Autowired
    public ManagerController(ManagerService managerService, StoreService storeService) {
        this.managerService = managerService;
        this.storeService = storeService;
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
    public ResponseEntity<ResponseMessage<Manager>> saveManager(@RequestBody Manager managerRequest) {
        try {
            Store store = storeService.findStoreById(managerRequest.getStore().getStoreId());

            if (store == null) {
                String errorMessage = "No se encontró la tienda con ID: " + managerRequest.getStore().getStoreId();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(errorMessage, null));
            }

            managerRequest.setStore(store);
            Manager savedManager = managerService.saveManager(managerRequest);

            String message = "Manager creado correctamente";
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage<>(message, savedManager));
        } catch (DataIntegrityViolationException e) {
            // Log de la excepción o manejo específico según sea necesario
            String errorMessage = "Error al ingresar la storeId, ya existe un manager con la store especificada";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage<>(errorMessage, null));
        } catch (Exception e) {
            // Log de la excepción o manejo específico según sea necesario
            String errorMessage = "Error interno al procesar la solicitud: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage<>(errorMessage, null));
        }
    }

    @PostMapping("/updateManager")
    public ResponseEntity<Manager> updateManager(@PathVariable Long id, @RequestBody Manager manager){
        Manager updatedManager = managerService.updateManager(id,manager);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedManager);
    }
}
