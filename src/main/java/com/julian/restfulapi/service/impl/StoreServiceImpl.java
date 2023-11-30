package com.julian.restfulapi.service.impl;

import com.julian.restfulapi.entity.Manager;
import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.error.local.ManagerNotFoundException;
import com.julian.restfulapi.error.local.StoreNotFoundException;
import com.julian.restfulapi.repository.ManagerRepository;
import com.julian.restfulapi.repository.StoreRepository;
import com.julian.restfulapi.service.StoreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, ManagerRepository managerRepository) {
        this.storeRepository = storeRepository;
        this.managerRepository = managerRepository;
    }


    @Override
    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<Store> findByNameIgnoreCase(String name) {
        return storeRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Store findStoreById(Long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        return storeOptional.orElseThrow(() -> new StoreNotFoundException("No se encontro la store con la id indicada"));
    }

    @Transactional
    @Override
    public Store saveStore(Store store) {
        try {
            // Verificar si se proporcionó la ID del gerente
            if (store.getManager() != null && store.getManager().getManagerId() != null) {
                // Buscar el gerente por ID
                Optional<Manager> optionalManager = managerRepository.findById(store.getManager().getManagerId());

                if (optionalManager.isPresent()) {
                    // Asignar el gerente existente a la tienda
                    store.setManager(optionalManager.get());
                } else {
                    // Manejar el caso en el que la ID del gerente no existe
                    throw new ManagerNotFoundException("No se encontró un manager con la ID proporcionada: " + store.getManager().getManagerId());
                }
            }

            store = storeRepository.save(store);

        } catch (RuntimeException e) {
            // Manejar la excepción original aquí o volver a lanzarla si es necesario
            if (e.getCause() instanceof ManagerNotFoundException) {
                throw (ManagerNotFoundException) e.getCause();
            }
            throw e; // Volver a lanzar cualquier otra excepción
        }
        return store;
    }

    @Transactional
    @Override
    public Store updateStore(Long id, Store updatedStore) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        if (storeOptional.isPresent()) {
            Store existingStore = storeOptional.get();

            existingStore.setName(updatedStore.getName());
            existingStore.setFloor(updatedStore.getFloor());


            return storeRepository.save(existingStore);
        } else {
            throw new StoreNotFoundException("Store con ID " + id + " no encontrado");
        }

    }

    @Override
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }



}
