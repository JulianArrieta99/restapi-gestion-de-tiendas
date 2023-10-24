package com.julian.restfulapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greeting")
public class GreetingController {

    @GetMapping("/sayHelloPublic")
    public String sayHelloPublic(){
        return "Hello from api";
    }
    @GetMapping("/sayHelloProtected")
    public String sayHelloProtected(){
        return "Hello from api protected";
    }

}
