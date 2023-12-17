package org.eblood.healthservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {



    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Endpoint reached !");
        return "Hello Azure!";
    }
}
