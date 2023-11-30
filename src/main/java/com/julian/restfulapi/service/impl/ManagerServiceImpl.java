package com.julian.restfulapi.service.impl;

import com.julian.restfulapi.entity.Manager;
import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.error.local.ManagerNotFoundException;
import com.julian.restfulapi.error.local.StoreNotFoundException;
import com.julian.restfulapi.repository.ManagerRepository;
import com.julian.restfulapi.service.ManagerService;
import com.julian.restfulapi.service.StoreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> findAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public Optional<Manager> findManagerById(Long id) {
        return managerRepository.findById(id);
    }

    @Override
    public Optional<Manager> findManagerByManagerNameIgnoreCase(String name) {
        return managerRepository.findManagerByManagerNameIgnoreCase(name);
    }

    @Transactional
    @Override
    public Manager saveManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Transactional
    @Override
    public Manager updateManager(Long id, Manager updatedManager) {
        Optional<Manager> managerOptional = managerRepository.findById(id);

        if (managerOptional.isPresent()) {
            Manager existingManager = managerOptional.get();

            existingManager.setManagerName(updatedManager.getManagerName());
            existingManager.setManagerLastName(updatedManager.getManagerLastName());


            return managerRepository.save(existingManager);
        } else {
            throw new ManagerNotFoundException("Manager con ID " + id + " no encontrado");
        }
    }


    @Override
    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }
}
