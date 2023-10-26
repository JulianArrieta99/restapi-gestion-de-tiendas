package com.julian.restfulapi.service.impl;

import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.error.local.StoreNotFoundException;
import com.julian.restfulapi.repository.StoreRepository;
import com.julian.restfulapi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final MessageSource messageSource;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, MessageSource messageSource) {
        this.storeRepository = storeRepository;
        this.messageSource = messageSource;
    }


    @Override
    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store saveStore(Store store) {

            return storeRepository.save(store);

    }

    @Override
    public Store updateStore(Long id, Store store) {
        Store storeDb = storeRepository.findById(id).get();
        if(Objects.nonNull(store.getFloor()) && !"".equalsIgnoreCase(store.getFloor())){
            storeDb.setFloor(store.getFloor());
        }
        if(Objects.nonNull(store.getName()) && !"".equalsIgnoreCase(store.getName())){
            storeDb.setName(store.getName());
        }
        return storeRepository.save(storeDb);
    }

    @Override
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

  /*  @Override
    public Optional<Store> findStoreByNameWithJPQL(String name) {
        return storeRepository.findStoreByNameWithJPQL(name);
    }*/


    @Override
    public Optional<Store> findByNameIgnoreCase(String name) {
        return storeRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Store findStoreById(Long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        return storeOptional.orElseThrow(() -> new StoreNotFoundException(
                messageSource.getMessage("error.entity.notfound", new Object[]{id}, Locale.getDefault())
        ));
    }


}
