package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional <Manager> findManagerByManagerNameIgnoreCase(String name);
}
