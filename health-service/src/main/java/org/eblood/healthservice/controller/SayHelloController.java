package org.eblood.healthservice.controller;

import io.swagger.client.api.FxSpotApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {


    FxSpotApi fxSpotApi = null;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello !";
    }
}
