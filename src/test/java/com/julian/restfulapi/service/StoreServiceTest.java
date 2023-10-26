package com.julian.restfulapi.service;

import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StoreServiceTest {

    private final StoreService storeService;

    @Autowired
    StoreServiceTest(StoreService storeService) {
        this.storeService = storeService;
    }
    @MockBean
    private StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
        Store store = Store.builder()
                .name("PetShop")
                .floor("Second Floor")
                .build();

        Mockito.when(storeRepository.findByNameIgnoreCase("PetShop")).thenReturn(Optional.of(store));
    }


    @Test
    @DisplayName("Prueba de obtencion de informacion de un store enviando un nombre válido")
    public void findByNameIgnoreCaseShouldFound(){
        String localName = "PetShop";
        Store store = storeService.findByNameIgnoreCase(localName).get();
        assertEquals(localName, store.getName());
        System.out.println("store = " + store);
    }
}