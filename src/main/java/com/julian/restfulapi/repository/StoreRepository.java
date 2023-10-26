package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    // Consulta con JPQL
  //  @Query("SELECT l FROM Store l WHERE l.name = :name")
   // Optional<Store> findLocalByNameWithJPQL(String name);

    // Consulta con Inversión de Control
    Optional<Store> findByName(String name);

    Optional<Store> findByNameIgnoreCase(String name);

}
