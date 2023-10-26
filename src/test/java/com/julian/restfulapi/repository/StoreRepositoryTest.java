package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Customer;
import com.julian.restfulapi.entity.Store;
import com.julian.restfulapi.entity.Manager;
import com.julian.restfulapi.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class StoreRepositoryTest {

   private final StoreRepository storeRepository;
   private Manager manager;
   private Store store;

   @Autowired
    public StoreRepositoryTest(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
   }

   @Test
   public void saveStore(){
        manager = manager.builder()
               .firstName("Juan")
               .lastName("Lopez")
               .build();
       store = store.builder()
               .name("PetShop")
               .floor("Second Floor")
               .manager(manager)
               .build();
       storeRepository.save(store);
   }

   @Test
   public void findAllStore(){
       List<Store> storeList = storeRepository.findAll();
       storeList.forEach(System.out::println);
   }
   @Test
   public void saveStoreWithOrders(){
       Order order1 = Order.builder()
               .description("Entrada Pelicula 1 sala 2 en 2D")
               .price(5.50)
               .build();
       Order order2 = Order.builder()
               .description("Entrada Pelicula 2 sala 5 en 3D")
               .price(8.00)
               .build();
       manager = manager.builder()
               .firstName("Arturo")
               .lastName("Lopeziano")
               .build();
       Store store = Store.builder()
               .name("Cinema")
               .floor("Third Floor")
               .manager(manager)
             //  .orderList(List.of(order1, order2))
               .build();
        storeRepository.save(store);

   }

   @Test
   public void findAllStoreWithOrders(){
       List<Store> storeList = storeRepository.findAll();
       System.out.println(storeList);
   }

   @Test
    public void saveStoreWithCustomers(){
       Customer customer1 = Customer.builder().firstName("Carl").lastName("Jhonson")
               .email("carl2@mail.com").build();
       Customer customer2 = Customer.builder().firstName("Eddie").lastName("Pulaski")
               .email("eddie2@mail.com").build();
       Store store = Store.builder().name("Clukin2 Bell")
               .floor("First Floor").customerList(List.of(customer1,customer2)).build();
       storeRepository.save(store);
   }
   @Test
    public void findAllStoresWithCustomers(){
       List<Store> storeList = storeRepository.findAll();
       System.out.println("storeList = " + storeList);
   }

   @Test
    public void findCustomerByStoreId(){
       // como trae un optional tenes q poner .get
       Store store = storeRepository.findById(10L).get();
       List<Customer> customerList = store.getCustomerList();
       System.out.println("customerList = " + customerList);
   }

}