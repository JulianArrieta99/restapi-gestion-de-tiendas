package com.julian.restfulapi.service;

import com.julian.restfulapi.entity.Local;
import com.julian.restfulapi.repository.LocalRepository;
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
class LocalServiceTest {

    private final LocalService localService;

    @Autowired
    LocalServiceTest(LocalService localService) {
        this.localService = localService;
    }
    @MockBean
    private LocalRepository localRepository;

    @BeforeEach
    void setUp() {
        Local local = Local.builder()
                .name("PetShop")
                .floor("Second Floor")
                .build();

        Mockito.when(localRepository.findByNameIgnoreCase("PetShop")).thenReturn(Optional.of(local));
    }


    @Test
    @DisplayName("Prueba de obtencion de informacion de un local enviando un nombre válido")
    public void findByNameIgnoreCaseShouldFound(){
        String localName = "PetShop";
        Local local = localService.findByNameIgnoreCase(localName).get();
        assertEquals(localName, local.getName());
        System.out.println("local = " + local);
    }
}