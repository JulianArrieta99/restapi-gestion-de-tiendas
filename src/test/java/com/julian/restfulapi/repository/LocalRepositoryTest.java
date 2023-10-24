package com.julian.restfulapi.repository;

import com.julian.restfulapi.entity.Customer;
import com.julian.restfulapi.entity.Local;
import com.julian.restfulapi.entity.Manager;
import com.julian.restfulapi.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class LocalRepositoryTest {

   private final LocalRepository localRepository;
   private Manager manager;
   private Local local;

   @Autowired
    public LocalRepositoryTest(LocalRepository localRepository) {
        this.localRepository = localRepository;
   }

   @Test
   public void saveLocal(){
        manager = manager.builder()
               .firstName("Juan")
               .lastName("Lopez")
               .build();
       local = local.builder()
               .name("PetShop")
               .floor("Second Floor")
               .manager(manager)
               .build();
       localRepository.save(local);
   }

   @Test
   public void findAllLocals(){
       List<Local> localList = localRepository.findAll();
       localList.forEach(System.out::println);
   }
   @Test
   public void saveLocalWithOrders(){
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
       Local local = Local.builder()
               .name("Cinema")
               .floor("Third Floor")
               .manager(manager)
             //  .orderList(List.of(order1, order2))
               .build();
        localRepository.save(local);

   }

   @Test
   public void findAllLocalsWithOrders(){
       List<Local> localList = localRepository.findAll();
       System.out.println(localList);
   }

   @Test
    public void saveLocalWithCustomers(){
       Customer customer1 = Customer.builder().firstName("Carl").lastName("Jhonson")
               .email("carl2@mail.com").build();
       Customer customer2 = Customer.builder().firstName("Eddie").lastName("Pulaski")
               .email("eddie2@mail.com").build();
       Local local = Local.builder().name("Clukin2 Bell")
               .floor("First Floor").customerList(List.of(customer1,customer2)).build();
       localRepository.save(local);
   }
   @Test
    public void findAllLocalsWithCustomers(){
       List<Local> localList = localRepository.findAll();
       System.out.println("localList = " + localList);
   }

   @Test
    public void findCustomerByLocalId(){
       // como trae un optional tenes q poner .get
       Local local = localRepository.findById(10L).get();
       List<Customer> customerList = local.getCustomerList();
       System.out.println("customerList = " + customerList);
   }

}