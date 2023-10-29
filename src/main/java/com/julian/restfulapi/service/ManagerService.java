package com.julian.restfulapi.service;



import com.julian.restfulapi.entity.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    List<Manager> findAllManagers();
    Optional<Manager> findManagerById(Long id);
    Optional<Manager> findManagerByManagerNameIgnoreCase(String name);
    Manager saveManager(Manager manager);
    Manager updateManager(Long id, Manager manager);
    void deleteManager(Long id);
}
