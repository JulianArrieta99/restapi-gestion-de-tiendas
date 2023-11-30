package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ManagerRepositoryTest {

    private final ManagerRepository managerRepository;

    @Autowired
    ManagerRepositoryTest(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Test
    public void findAllManagers (){
        List<Manager> managerList = managerRepository.findAll();
        managerList.forEach(System.out::println);
    }
    @Test
    public void findManagerByName (){
        managerRepository.findManagerByManagerNameIgnoreCase("carlo");
    }
}