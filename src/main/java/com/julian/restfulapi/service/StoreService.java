package com.julian.restfulapi.service;


import com.julian.restfulapi.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    List<Store> findAllStores();
    Store saveStore(Store store);
    Store updateStore(Long id, Store store);
    void deleteStore(Long id);
  //  Optional<Store> findLocalByNameWithJPQL(String name);
    Optional<Store> findByNameIgnoreCase(String name);
    Store findStoreById(Long id);
}