package com.rnd.corp.springpocketapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    String hello() {
        return "Hello pocket";
    }
}
