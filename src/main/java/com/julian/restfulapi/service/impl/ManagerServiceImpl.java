package com.julian.restfulapi.service.impl;

import com.julian.restfulapi.entity.Manager;
import com.julian.restfulapi.repository.ManagerRepository;
import com.julian.restfulapi.service.ManagerService;
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
    public Manager saveManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager updateManager(Long id, Manager manager) {
        Optional<Manager> managerDB = managerRepository.findById(id);
        return managerRepository.save(manager);
    }

    @Override
    public Optional<Manager> findManagerById(Long id) {
        return managerRepository.findById(id);
    }

    @Override
    public Optional<Manager> findManagerByManagerNameIgnoreCase(String name) {
        return managerRepository.findManagerByManagerNameIgnoreCase(name);
    }

    @Override
    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }
}
